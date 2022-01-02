package fr.xebia.jqwik.exercise6;

import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Group;
import net.jqwik.api.Property;

import java.time.Instant;

import static fr.xebia.jqwik.exercise6.ValiditySpan.between;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class ValiditySpanTest {

    @Group
    class InitializeValiditySpan {

        @Property
        void should_initialize_if_start_date_is_before_end_date(@ForAll final Instant start, @ForAll final Instant end) {
            Assume.that(start.isBefore(end));

            final ValiditySpan validitySpan = between(start).and(end);

            assertSoftly(softly -> {
                softly.assertThat(validitySpan.start()).isEqualTo(start);
                softly.assertThat(validitySpan.end()).isEqualTo(end);
            });
        }

        @Property
        void should_initialize_if_start_date_is_the_same_as_end_date(@ForAll final Instant startAndEnd) {
            final ValiditySpan validitySpan = between(startAndEnd).and(startAndEnd);

            assertSoftly(softly -> {
                softly.assertThat(validitySpan.start()).isEqualTo(startAndEnd);
                softly.assertThat(validitySpan.end()).isEqualTo(startAndEnd);
            });
        }

        @Property
        void should_fail_to_initialize_if_start_date_is_after_end_date(@ForAll final Instant start, @ForAll final Instant end) {
            Assume.that(start.isAfter(end));

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> between(start).and(end))
                    .withMessage("Start date must be before end date");
        }

        @Property
        void should_fail_to_initialize_if_start_date_is_null(@ForAll final Instant end) {
            assertThatNullPointerException()
                    .isThrownBy(() -> between(null).and(end));
        }

        @Property
        void should_fail_to_initialize_if_end_date_is_null(@ForAll final Instant start) {
            assertThatNullPointerException()
                    .isThrownBy(() -> between(start).and(null));
        }
    }

    @Group
    class ValiditySpanContainsDate {

        @Property
        void should_fail_to_contain_null_value(@ForAll ValiditySpan validitySpan) {
            assertThatNullPointerException().isThrownBy(() -> validitySpan.contains(null));
        }

        @Property
        void should_contain_value_between_start_date_and_end_date(@ForAll final ValiditySpan validitySpan, @ForAll final Instant value) {
            Assume.that(value.isAfter(validitySpan.start()));
            Assume.that(value.isBefore(validitySpan.end()));

            assertThat(validitySpan.contains(value)).isTrue();
        }

        @Property
        void should_not_contain_value_before_start_date(@ForAll final ValiditySpan validitySpan, @ForAll final Instant value) {
            Assume.that(value.isBefore(validitySpan.start()));

            assertThat(validitySpan.contains(value)).isFalse();
        }

        @Property
        void should_not_contain_value_after_end_date(@ForAll final ValiditySpan validitySpan, @ForAll final Instant value) {
            Assume.that(value.isAfter(validitySpan.end()));

            assertThat(validitySpan.contains(value)).isFalse();
        }

        @Property
        void should_contain_start_date(@ForAll ValiditySpan validitySpan) {
            assertThat(validitySpan.contains(validitySpan.start())).isTrue();
        }

        @Property
        void should_contain_end_date(@ForAll ValiditySpan validitySpan) {
            assertThat(validitySpan.contains(validitySpan.end())).isTrue();
        }
    }

}
