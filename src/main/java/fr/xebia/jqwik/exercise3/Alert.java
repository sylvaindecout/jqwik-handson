package fr.xebia.jqwik.exercise3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
final class Alert {

    @NonNull Type type;
    @NonNull String country;

    @Getter
    @AllArgsConstructor(access = PRIVATE)
    public enum Type {
        EMPTY_STOCK(1, "00001", "Empty stock"),
        INVALID_REQUEST(2, "00049", "Invalid request");

        @NonNull int codeForItaly;
        @NonNull String codeForUsa;
        @NonNull String defaultMessage;
    }

}
