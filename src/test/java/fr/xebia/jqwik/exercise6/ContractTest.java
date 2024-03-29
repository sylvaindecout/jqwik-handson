package fr.xebia.jqwik.exercise6;

import net.jqwik.api.Assume;
import net.jqwik.api.Disabled;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

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
        Assume.that(!contract.validitySpan().contains(incident.date()));

        assertThat(contract.covers(incident)).isFalse();
    }

    @Property
    void should_not_cover_incident_for_an_unexpected_vehicle_type(@ForAll Contract contract, @ForAll Incident incident) {
        Assume.that(!contract.coveredVehicleTypes().contains(incident.vehicle().type()));

        assertThat(contract.covers(incident)).isFalse();
    }

    @Property
    void should_not_cover_incident_in_an_unexpected_country(@ForAll Contract contract, @ForAll Incident incident) {
        Assume.that(!contract.coveredCountries().contains(incident.country()));

        assertThat(contract.covers(incident)).isFalse();
    }

    @Disabled("Don't forget to remove this annotation!")
    /**
     * TODO: Fix test so that it succeeds.
     * <hr/>
     * <p>Hint #1: Understand what it means for a property to be "exhausted". Cf. <a href="https://jqwik.net/docs/current/user-guide.html#assumptions">Assumptions</a></p>
     * <p>Hint #2: Group "contract" and "incident" arguments into a consistent "Tuple". Cf. <a href="https://jqwik.net/docs/current/user-guide.html#flat-mapping-with-tuple-types">Flat Mapping with Tuple Types</a></p>
     */
    @Property
    void should_cover_incident_matching_coverage(@ForAll Contract contract, @ForAll Incident incident) {
        Assume.that(contract.validitySpan().contains(incident.date()));
        Assume.that(contract.coveredVehicleTypes().contains(incident.vehicle().type()));
        Assume.that(contract.coveredCountries().contains(incident.country()));

        assertThat(contract.covers(incident)).isTrue();
    }

}
