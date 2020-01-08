package fr.xebia.jqwik.exercise6;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

final class VehicleType {

    private final int code;

    VehicleType(final int code) {
        checkArgument(code > -1,
                "Code is expected to be a positive value - value: %s", code);
        checkArgument(code < 20,
                "Code is expected to be a value lower than 20 - value: %s", code);
        this.code = code;
    }

    int getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleType that = (VehicleType) o;
        return code == that.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "VehicleType{" +
                "code=" + code +
                '}';
    }
}
