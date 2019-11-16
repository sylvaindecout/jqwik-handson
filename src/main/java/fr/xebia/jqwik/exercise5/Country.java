package fr.xebia.jqwik.exercise5;

import lombok.Value;

import static com.google.common.base.Preconditions.checkArgument;

@Value
final class Country {

    String code;

    Country(final String code) {
        checkArgument(code.length() == 2,
                "Length of country code ('%s') is invalid", code);
        this.code = code;
    }

}
