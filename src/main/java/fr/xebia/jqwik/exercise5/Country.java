package fr.xebia.jqwik.exercise5;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

final class Country {

    private final String code;

    Country(final String code) {
        checkArgument(code.length() == 2,
                "Length of country code ('%s') is invalid", code);
        this.code = code;
    }

    String getCode() {
        return this.code;
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
