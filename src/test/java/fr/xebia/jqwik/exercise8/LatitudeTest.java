package fr.xebia.jqwik.exercise8;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;
import net.jqwik.api.constraints.Negative;

import static java.lang.Math.toRadians;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class LatitudeTest {

    @Property
    void should_expose_value_in_degrees(@ForAll @DoubleRange(min = 0, max = 90) double value) {
        assertThat(Latitude.fromDegrees(value).asDegrees()).isEqualTo(value);
    }

    @Property
    void should_expose_value_in_radians(@ForAll @DoubleRange(min = 0, max = 90) double value) {
        assertThat(Latitude.fromDegrees(value).asRadians()).isEqualTo(toRadians(value));
    }

    @Property
    void should_fail_to_initialize_from_value_below_0(@ForAll @Negative double value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Latitude.fromDegrees(value))
                .withMessage("Latitude must be in [0.0..90.0]° (input value: %s°)", value);
    }

    @Property
    void should_fail_to_initialize_from_value_above_90(@ForAll @DoubleRange(min = 90.001) double value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Latitude.fromDegrees(value))
                .withMessage("Latitude must be in [0.0..90.0]° (input value: %s°)", value);
    }

    @Property
    void should_display_distance_as_meters(@ForAll Latitude latitude) {
        assertThat(latitude.toString())
                .isEqualTo("%s°", latitude.asDegrees());
    }
}
