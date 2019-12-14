package fr.xebia.jqwik.exercise6;

import lombok.Value;

import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;

@Value
final class Country {

    private static final Pattern pattern = Pattern.compile("\\p{Alpha}{2}");

    String code;

    Country(final String code) {
        checkArgument(pattern.matcher(code).matches(),
                "Country code is expected to consist in exactly 2 upper-case alphabetic characters - value: '%s'", code);
        this.code = code.toUpperCase();
    }

}
