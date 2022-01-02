package fr.xebia.jqwik.exercise6;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.StringLength;

import static org.assertj.core.api.Assertions.*;

class CountryTest {

    @Property
    void should_initialize_from_code_with_exactly_2_alpha_characters(@ForAll @StringLength(2) @AlphaChars final String code) {
        assertThat(new Country(code).code()).isEqualTo(code.toUpperCase());
    }

    @Property
    void should_fail_to_initialize_from_code_with_special_characters(@ForAll @StringLength(2) final String code) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Country(code))
                .withMessage("Country code is expected to consist in exactly 2 upper-case alphabetic characters - value: '%s'", code);
    }

    @Property
    void should_fail_to_initialize_from_code_with_more_than_2_characters(@ForAll @StringLength(min = 3) final String code) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Country(code))
                .withMessage("Country code is expected to consist in exactly 2 upper-case alphabetic characters - value: '%s'", code);
    }

    @Property
    void should_fail_to_initialize_from_code_with_less_than_2_characters(@ForAll @StringLength(max = 1) final String code) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Country(code))
                .withMessage("Country code is expected to consist in exactly 2 upper-case alphabetic characters - value: '%s'", code);
    }

    @Property
    void should_fail_to_initialize_from_null_code() {
        assertThatNullPointerException()
                .isThrownBy(() -> new Country(null));
    }

}
