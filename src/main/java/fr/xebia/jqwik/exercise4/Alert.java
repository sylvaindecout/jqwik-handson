package fr.xebia.jqwik.exercise4;

import static java.util.Objects.requireNonNull;

record Alert(Alert.Type type, Country country) {

    Alert {
        requireNonNull(type);
        requireNonNull(country);
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
