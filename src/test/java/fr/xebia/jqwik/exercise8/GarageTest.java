package fr.xebia.jqwik.exercise8;


import fr.xebia.jqwik.exercise8.geo.Coordinates;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class GarageTest {

    @Property
    void should_expose_name(@ForAll String name, @ForAll Coordinates coordinates) {
        assertThat(new Garage(name, coordinates).name()).isEqualTo(name);
    }

    @Property
    void should_expose_coordinates(@ForAll String name, @ForAll Coordinates coordinates) {
        assertThat(new Garage(name, coordinates).coordinates()).isEqualTo(coordinates);
    }

    @Property
    void should_fail_to_initialize_from_null_name(@ForAll Coordinates coordinates) {
        assertThatNullPointerException()
                .isThrownBy(() -> new Garage(null, coordinates));
    }

    @Property
    void should_fail_to_initialize_from_null_coordinated(@ForAll String name) {
        assertThatNullPointerException()
                .isThrownBy(() -> new Garage(name, null));
    }

}
