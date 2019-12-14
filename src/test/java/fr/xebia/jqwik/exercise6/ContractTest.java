package fr.xebia.jqwik.exercise6;

import net.jqwik.api.*;
import net.jqwik.api.Tuple.Tuple2;

import java.time.Instant;

import static net.jqwik.api.Arbitraries.*;
import static net.jqwik.api.Combinators.combine;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class ContractTest {

    @Property
    void should_fail_to_cover_null_incident(@ForAll Contract contract) {
        assertThatNullPointerException()
                .isThrownBy(() -> contract.covers(null));
    }

    @Property
    void should_not_cover_incident_outside_validity_period(@ForAll Contract contract, @ForAll Incident incident) {
        Assume.that(!contract.getValiditySpan().contains(incident.getDate()));

        assertThat(contract.covers(incident)).isFalse();
    }

    @Property
    void should_not_cover_incident_for_an_unexpected_vehicle_type(@ForAll Contract contract, @ForAll Incident incident) {
        Assume.that(!contract.getCoveredVehicleTypes().contains(incident.getVehicle().getType()));

        assertThat(contract.covers(incident)).isFalse();
    }

    @Property
    void should_not_cover_incident_in_an_unexpected_country(@ForAll Contract contract, @ForAll Incident incident) {
        Assume.that(!contract.getCoveredCountries().contains(incident.getCountry()));

        assertThat(contract.covers(incident)).isFalse();
    }

    /**
     * TODO: Fix test so that it succeeds.
     * <hr/>
     * <p>Hint #1: Understand what it means for a property to be "exhausted". Cf. <a href="https://jqwik.net/docs/current/user-guide.html#assumptions">Assumptions</a></p>
     * <p>Hint #2: Group "contract" and "incident" arguments into a consistent "Tuple". Cf. <a href="https://jqwik.net/docs/current/user-guide.html#flat-mapping-with-tuple-types">Flat Mapping with Tuple Types</a></p>
     */
    @Property
    void should_cover_incident_matching_coverage(@ForAll("contractAndIncidentWithSameCountry") Tuple2<Contract, Incident> contractAndIncident) {
        final Contract contract = contractAndIncident.get1();
        final Incident incident = contractAndIncident.get2();

        Assume.that(contract.getValiditySpan().contains(incident.getDate()));
        Assume.that(contract.getCoveredVehicleTypes().contains(incident.getVehicle().getType()));

        assertThat(contract.covers(incident)).isTrue();
    }

    @Provide
    Arbitrary<Tuple2<Contract, Incident>> contractAndIncidentWithSameCountry() {
        return defaultFor(Country.class)
                .flatMap(country -> incidentArbitraryWithCountry(country)
                        .flatMap(incident -> contractArbitraryWithCountry(country)
                                .map(contract -> Tuple.of(contract, incident))));
    }

    private static Arbitrary<Incident> incidentArbitraryWithCountry(final Country country) {
        return combine(
                strings(),
                of(Incident.Type.class),
                defaultFor(Instant.class),
                defaultFor(Vehicle.class)
        ).as((id, type, date, vehicle) ->
                Incident.builder()
                        .id(id)
                        .type(type)
                        .date(date)
                        .country(country)
                        .vehicle(vehicle)
                        .build()
        );
    }

    private static Arbitrary<Contract> contractArbitraryWithCountry(final Country country) {
        return combine(
                defaultFor(PolicyNumber.class),
                defaultFor(ValiditySpan.class),
                defaultFor(VehicleType.class).set().ofMaxSize(20),
                defaultFor(Country.class).set().filter(list -> list.contains(country))
        ).as((policyNumber, validitySpan, vehicleTypes, countries) ->
                Contract.builder()
                        .policyNumber(policyNumber)
                        .validitySpan(validitySpan)
                        .coveredCountries(countries)
                        .coveredVehicleTypes(vehicleTypes)
                        .build()
        );
    }

}
