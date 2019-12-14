package fr.xebia.jqwik.exercise6;

import lombok.NonNull;
import lombok.Value;

import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;

@Value
final class PolicyNumber {

    private static final Pattern pattern = Pattern.compile("\\p{Alnum}{6,12}");

    @NonNull String value;

    PolicyNumber(final String value){
        checkArgument(pattern.matcher(value).matches(),
                "Policy number must contain between 6 and 12 alphanumeric characters - value: '%s'", value);
        this.value = value;
    }

}
