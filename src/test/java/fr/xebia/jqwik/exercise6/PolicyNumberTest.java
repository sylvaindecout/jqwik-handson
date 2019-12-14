package fr.xebia.jqwik.exercise6;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NumericChars;
import net.jqwik.api.constraints.StringLength;

import static org.assertj.core.api.Assertions.*;

class PolicyNumberTest {

    @Property
    void should_initialize_from_valid_value(@ForAll @StringLength(min = 6, max = 12) @AlphaChars @NumericChars String value) {
        assertThat(new PolicyNumber(value).getValue()).isEqualTo(value);
    }

    @Property
    void should_fail_to_initialize_from_null_value() {
        assertThatNullPointerException()
                .isThrownBy(() -> new PolicyNumber(null));
    }

    @Property
    void should_fail_to_initialize_from_value_with_less_than_6_characters(@ForAll @StringLength(max = 5) @AlphaChars @NumericChars String value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PolicyNumber(value))
                .withMessage("Policy number must contain between 6 and 12 alphanumeric characters - value: '%s'", value);
    }

    @Property
    void should_fail_to_initialize_from_value_with_more_than_12_characters(@ForAll @StringLength(min = 13) @AlphaChars @NumericChars String value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PolicyNumber(value))
                .withMessage("Policy number must contain between 6 and 12 alphanumeric characters - value: '%s'", value);
    }

    @Property
    void should_fail_to_initialize_from_value_with_invalid_characters(@ForAll @StringLength(min = 6, max = 12) String value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PolicyNumber(value))
                .withMessage("Policy number must contain between 6 and 12 alphanumeric characters - value: '%s'", value);
    }

}
