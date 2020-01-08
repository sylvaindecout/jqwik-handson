package fr.xebia.jqwik.exercise6;

import java.util.Objects;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;

final class Country {

    private static final Pattern pattern = Pattern.compile("\\p{Alpha}{2}");

    private final String code;

    Country(final String code) {
        checkArgument(pattern.matcher(code).matches(),
                "Country code is expected to consist in exactly 2 upper-case alphabetic characters - value: '%s'", code);
        this.code = code.toUpperCase();
    }

    String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(code, country.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                '}';
    }
}
