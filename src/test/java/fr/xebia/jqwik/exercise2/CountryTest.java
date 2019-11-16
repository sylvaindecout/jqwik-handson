package fr.xebia.jqwik.exercise2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * TODO: Replace Example-Based Testing with Property-Based Testing.
 * <br/>
 * <p>Hint: <a href="https://jqwik.net/docs/current/user-guide.html#constraining-default-generation">Constraining default generation</a></p>
 */
class CountryTest {

    @Test
    void should_initialize_from_code_with_exactly_2_characters() {
        assertThat(new Country("FR").getCode()).isEqualTo("FR");
    }

    @Test
    void should_fail_to_initialize_from_code_with_more_than_2_characters() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Country("FRA"));
    }

    @Test
    void should_fail_to_initialize_from_code_with_less_than_2_characters() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Country("F"));
    }

    @Test
    void should_fail_to_initialize_from_null_code() {
        assertThatNullPointerException().isThrownBy(() -> new Country(null));
    }

}
