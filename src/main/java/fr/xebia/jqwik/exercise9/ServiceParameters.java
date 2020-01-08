package fr.xebia.jqwik.exercise9;

import java.util.Objects;

final class ServiceParameters {

    private final String value;

    ServiceParameters(final String value) {
        this.value = value;
    }

    String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceParameters that = (ServiceParameters) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ServiceParameters{" +
                "value='" + value + '\'' +
                '}';
    }
}
