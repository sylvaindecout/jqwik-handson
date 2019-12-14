package fr.xebia.jqwik.exercise2;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.StringLength;

import static org.assertj.core.api.Assertions.*;

/**
 * TODO: Replace Example-Based Testing with Property-Based Testing.
 * <hr/>
 * <p>Hint: Use annotations in order to apply constraints to parameter generation. Cf. <a href="https://jqwik.net/docs/current/user-guide.html#constraining-default-generation">Constraining default generation</a></p>
 */
class CountryTest {

    @Property
    void should_initialize_from_code_with_exactly_2_characters(@ForAll @StringLength(2) String code) {
        assertThat(new Country(code).code()).isEqualTo(code);
    }

    @Property
    void should_fail_to_initialize_from_code_with_more_than_2_characters(@ForAll @StringLength(min = 3) String code) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Country(code));
    }

    @Property
    void should_fail_to_initialize_from_code_with_less_than_2_characters(@ForAll @StringLength(max = 1) String code) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Country(code));
    }

    @Property
    void should_fail_to_initialize_from_null_code() {
        assertThatNullPointerException().isThrownBy(() -> new Country(null));
    }

}
