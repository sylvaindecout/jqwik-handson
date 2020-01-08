package fr.xebia.jqwik.exercise8.geo;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.format;

public final class Distance {

    public static final Distance ZERO = new Distance(0, false);
    public static final Distance ERROR = new Distance(-1, true);

    private final long meters;
    private final boolean error;

    private Distance(final long meters, final boolean error) {
        this.meters = meters;
        this.error = error;
    }

    public static Distance fromMeters(final long meters) {
        checkArgument(meters >= 0,
                "Distance must be positive (input value: %s meters)", meters);
        return new Distance(meters, false);
    }

    public static Distance fromKilometers(final double kilometers) {
        checkArgument(kilometers >= 0,
                "Distance must be positive (input value: %s km)", kilometers);
        final long meters = Double.valueOf(kilometers * 1_000).longValue();
        return new Distance(meters, false);
    }

    public long asMeters() {
        return this.meters;
    }

    public boolean isError() {
        return this.error;
    }

    public Distance minus(final Distance distance) {
        return this.isError() || distance.isError() || distance.isGreaterThan(this)
                ? ERROR
                : Distance.fromMeters(this.asMeters() - distance.asMeters());
    }

    public boolean isGreaterThan(final Distance distance) {
        return !this.isError()
                && !distance.isError()
                && this.asMeters() > distance.asMeters();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return meters == distance.meters &&
                error == distance.error;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meters, error);
    }

    @Override
    public String toString() {
        return this.isError() ? "ERROR"
                : format("%s meters", this.asMeters());
    }
}
