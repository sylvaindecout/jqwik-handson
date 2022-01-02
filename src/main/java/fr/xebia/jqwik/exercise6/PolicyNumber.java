package fr.xebia.jqwik.exercise6;

import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

record PolicyNumber(String value) {

    private static final Pattern pattern = Pattern.compile("\\p{Alnum}{6,12}");

    PolicyNumber {
        requireNonNull(value);
        checkArgument(pattern.matcher(value).matches(),
                "Policy number must contain between 6 and 12 alphanumeric characters - value: '%s'", value);
    }

}
