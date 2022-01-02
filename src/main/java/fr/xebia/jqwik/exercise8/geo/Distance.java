package fr.xebia.jqwik.exercise8.geo;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.format;

public record Distance(long asMeters, boolean error) {

    public static final Distance ZERO = new Distance(0, false);
    public static final Distance ERROR = new Distance(-1, true);

    public static Distance fromMeters(final long meters) {
        checkArgument(meters >= 0,
                "Distance must be positive (input value: %s meters)", meters);
        return new Distance(meters, false);
    }

    public static Distance fromKilometers(final double kilometers) {
        checkArgument(kilometers >= 0,
                "Distance must be positive (input value: %s km)", kilometers);
        final var meters = Double.valueOf(kilometers * 1_000).longValue();
        return new Distance(meters, false);
    }

    public Distance minus(final Distance distance) {
        return this.error() || distance.error() || distance.isGreaterThan(this)
                ? ERROR
                : Distance.fromMeters(this.asMeters() - distance.asMeters());
    }

    public boolean isGreaterThan(final Distance distance) {
        return !this.error()
                && !distance.error()
                && this.asMeters() > distance.asMeters();
    }

    @Override
    public String toString() {
        return this.error() ? "ERROR"
                : format("%s meters", this.asMeters());
    }
}
