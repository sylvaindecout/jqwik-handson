package fr.xebia.jqwik.exercise8;

import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Group;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;
import net.jqwik.api.constraints.LongRange;
import net.jqwik.api.constraints.Negative;
import net.jqwik.api.constraints.Scale;

import static fr.xebia.jqwik.exercise8.Distance.ERROR;
import static fr.xebia.jqwik.exercise8.Distance.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class DistanceTest {

    @Group
    class Initialization {

        @Property
        void should_initialize_from_valid_value_in_meters(@ForAll @LongRange long value) {
            assertThat(Distance.fromMeters(value).asMeters()).isEqualTo(value);
        }

        @Property
        void should_fail_to_initialize_from_negative_value_in_meters(@ForAll @Negative long value) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Distance.fromMeters(value))
                    .withMessage("Distance must be positive (input value: %s meters)", value);
        }

        @Property
        void should_initialize_from_valid_value_in_kilometers(@ForAll @DoubleRange @Scale(3) double value) {
            assertThat(Distance.fromKilometers(value).asMeters()).isEqualTo(toMetersWithNoDecimal(value));
        }

        @Property
        void should_fail_to_initialize_from_negative_value_in_kilometers(@ForAll @Negative double value) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Distance.fromKilometers(value))
                    .withMessage("Distance must be positive (input value: %s km)", value);
        }

        private long toMetersWithNoDecimal(final double kilometers) {
            return Double.valueOf(kilometers * 1000).longValue();
        }
    }

    @Group
    class Comparison {

        @Property
        void should_not_consider_ERROR_as_greater_than_other_distance(@ForAll Distance otherDistance) {
            assertThat(ERROR.isGreaterThan(otherDistance)).isFalse();
        }

        @Property
        void should_not_consider_distance_as_greater_than_ERROR(@ForAll Distance distance) {
            assertThat(distance.isGreaterThan(ERROR)).isFalse();
        }

        @Property
        void should_not_consider_distance_as_greater_than_greater_distance(@ForAll Distance distance, @ForAll Distance otherDistance) {
            Assume.that(distance.asMeters() < otherDistance.asMeters());

            assertThat(distance.isGreaterThan(otherDistance)).isFalse();
        }

        @Property
        void should_not_consider_distance_as_greater_than_equal_distance(@ForAll @LongRange long value) {
            final Distance distance = Distance.fromMeters(value);
            final Distance otherDistance = Distance.fromMeters(value);

            assertThat(distance.isGreaterThan(otherDistance)).isFalse();
        }

        @Property
        void should_consider_distance_as_greater_than_lesser_distance(@ForAll Distance distance, @ForAll Distance otherDistance) {
            Assume.that(distance.asMeters() > otherDistance.asMeters());

            assertThat(distance.isGreaterThan(otherDistance)).isTrue();
        }
    }

    @Group
    class Subtraction {

        @Property
        void should_fail_to_subtract_distance_from_ERROR(@ForAll Distance otherDistance) {
            assertThat(ERROR.minus(otherDistance)).isEqualTo(ERROR);
        }

        @Property
        void should_fail_to_subtract_ERROR_from_distance(@ForAll Distance distance) {
            assertThat(distance.minus(ERROR)).isEqualTo(ERROR);
        }

        @Property
        void should_fail_to_subtract_distance_from_lesser_distance(@ForAll Distance distance, @ForAll Distance otherDistance) {
            Assume.that(distance.asMeters() < otherDistance.asMeters());

            assertThat(distance.minus(otherDistance)).isEqualTo(ERROR);
        }

        @Property
        void should_subtract_distance_from_equal_distance(@ForAll @LongRange long value) {
            final Distance distance = Distance.fromMeters(value);
            final Distance otherDistance = Distance.fromMeters(value);

            assertThat(distance.minus(otherDistance)).isEqualTo(ZERO);
        }

        @Property
        void should_subtract_distance_from_greater_distance(@ForAll Distance distance, @ForAll Distance otherDistance) {
            Assume.that(distance.asMeters() > otherDistance.asMeters());

            assertThat(distance.minus(otherDistance).asMeters())
                    .isEqualTo(distance.asMeters() - otherDistance.asMeters());
        }
    }

    @Group
    class ToString {

        @Property
        void should_display_distance_as_meters(@ForAll Distance distance) {
            assertThat(distance.toString())
                    .isEqualTo("%s meters", distance.asMeters());
        }

        @Property
        void should_display_error_as_such() {
            assertThat(ERROR.toString()).isEqualTo("ERROR");
        }
    }

}
