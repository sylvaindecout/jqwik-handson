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

    @Disabled
    /**
     * TODO: Fix test so that it succeeds.
     * <hr/>
     * <p>Hint #1: <a href="https://jqwik.net/docs/current/user-guide.html#assumptions">Assumptions</a></p>
     * <p>Hint #2: <a href="https://jqwik.net/docs/current/user-guide.html#flat-mapping-with-tuple-types">Flat Mapping with Tuple Types</a></p>
     */
    @Property
    void should_cover_incident_matching_coverage(@ForAll Contract contract, @ForAll Incident incident) {
        Assume.that(contract.getValiditySpan().contains(incident.getDate()));
        Assume.that(contract.getCoveredVehicleTypes().contains(incident.getVehicle().getType()));
        Assume.that(contract.getCoveredCountries().contains(incident.getCountry()));

        assertThat(contract.covers(incident)).isTrue();
    }

}
