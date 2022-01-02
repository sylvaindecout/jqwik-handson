package fr.xebia.jqwik.exercise8;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class IncidentTest {

    @Property
    void should_expose_type(@ForAll IncidentType type, @ForAll Location location) {
        assertThat(new Incident(type, location).type()).isEqualTo(type);
    }

    @Property
    void should_expose_location(@ForAll IncidentType type, @ForAll Location location) {
        assertThat(new Incident(type, location).location()).isEqualTo(location);
    }

    @Property
    void should_fail_to_initialize_from_null_type(@ForAll Location location) {
        assertThatNullPointerException()
                .isThrownBy(() -> new Incident(null, location));
    }

    @Property
    void should_fail_to_initialize_from_null_coordinated(@ForAll IncidentType type) {
        assertThatNullPointerException()
                .isThrownBy(() -> new Incident(type, null));
    }

}
