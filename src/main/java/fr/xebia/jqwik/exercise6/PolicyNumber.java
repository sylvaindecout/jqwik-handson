package fr.xebia.jqwik.exercise6;

import java.util.Objects;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

final class PolicyNumber {

    private static final Pattern pattern = Pattern.compile("\\p{Alnum}{6,12}");

    private final String value;

    PolicyNumber(final String value) {
        requireNonNull(value);
        checkArgument(pattern.matcher(value).matches(),
                "Policy number must contain between 6 and 12 alphanumeric characters - value: '%s'", value);
        this.value = value;
    }

    String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicyNumber that = (PolicyNumber) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "PolicyNumber{" +
                "value='" + value + '\'' +
                '}';
    }
}
