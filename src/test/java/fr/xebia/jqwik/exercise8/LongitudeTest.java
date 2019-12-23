package fr.xebia.jqwik.exercise8;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;

import static java.lang.Math.toRadians;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class LongitudeTest {

    @Property
    void should_expose_value_in_degrees(@ForAll @DoubleRange(min = -179.999, max = 180) double value) {
        assertThat(Longitude.fromDegrees(value).asDegrees()).isEqualTo(value);
    }

    @Property
    void should_expose_value_in_radians(@ForAll @DoubleRange(min = -179.999, max = 180) double value) {
        assertThat(Longitude.fromDegrees(value).asRadians()).isEqualTo(toRadians(value));
    }

    @Property
    void should_fail_to_initialize_from_value_below_0(@ForAll @DoubleRange(min = -Double.MAX_VALUE, max = -180) double value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Longitude.fromDegrees(value))
                .withMessage("Longitude must be in (-180.0..180.0]° (input value: %s°)", value);
    }

    @Property
    void should_fail_to_initialize_from_value_above_90(@ForAll @DoubleRange(min = 180.001) double value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Longitude.fromDegrees(value))
                .withMessage("Longitude must be in (-180.0..180.0]° (input value: %s°)", value);
    }

    @Property
    void should_display_distance_as_meters(@ForAll Longitude longitude) {
        assertThat(longitude.toString())
                .isEqualTo("%s°", longitude.asDegrees());
    }
}
