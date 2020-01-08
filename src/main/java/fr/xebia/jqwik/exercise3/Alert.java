package fr.xebia.jqwik.exercise3;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

final class Alert {

    private final Type type;
    private final String country;

    Alert(final Type type, final String country) {
        this.type = requireNonNull(type);
        this.country = requireNonNull(country);
    }

    Type getType() {
        return this.type;
    }

    String getCountry() {
        return this.country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alert alert = (Alert) o;
        return type == alert.type &&
                Objects.equals(country, alert.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, country);
    }

    @Override
    public String toString() {
        return "Alert{" +
                "type=" + type +
                ", country='" + country + '\'' +
                '}';
    }

    enum Type {
        EMPTY_STOCK(1, "00001", "Empty stock"),
        INVALID_REQUEST(2, "00049", "Invalid request");

        private final int codeForItaly;
        private final String codeForUsa;
        private final String defaultMessage;

        Type(final int codeForItaly, final String codeForUsa, final String defaultMessage) {
            this.codeForItaly = codeForItaly;
            this.codeForUsa = codeForUsa;
            this.defaultMessage = defaultMessage;
        }

        int getCodeForItaly() {
            return this.codeForItaly;
        }

        String getCodeForUsa() {
            return this.codeForUsa;
        }

        String getDefaultMessage() {
            return this.defaultMessage;
        }
    }

}
