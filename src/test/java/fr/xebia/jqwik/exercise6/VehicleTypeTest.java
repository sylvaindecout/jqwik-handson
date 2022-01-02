package fr.xebia.jqwik.exercise6;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Negative;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class VehicleTypeTest {

    @Property
    void should_initialize_from_valid_code(@ForAll @IntRange(max = 19) int code) {
        assertThat(new VehicleType(code).code()).isEqualTo(code);
    }

    @Property
    void should_fail_to_initialize_from_negative_code(@ForAll @Negative int code) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new VehicleType(code))
                .withMessage("Code is expected to be a positive value - value: %s", code);
    }

    @Property
    void should_fail_to_initialize_from_code_greater_than_19(@ForAll @IntRange(min = 20) int code) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new VehicleType(code))
                .withMessage("Code is expected to be a value lower than 20 - value: %s", code);
    }

}
