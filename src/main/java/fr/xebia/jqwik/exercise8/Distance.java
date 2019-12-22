package fr.xebia.jqwik.exercise8;

import lombok.Value;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.format;

@Value
final class Distance {

    public static final Distance ZERO = new Distance(0);
    public static final Distance ERROR = new Distance(0, true);

    long meters;
    boolean error;

    private Distance(final long meters, final boolean error) {
        checkArgument(meters >= 0,
                "Distance must be positive (input value: %s)", meters);
        this.meters = meters;
        this.error = error;
    }

    public Distance(final long meters) {
        this(meters, false);
    }

    Distance minus(final Distance distance) {
        return this.error || distance.error || distance.isGreaterThan(this)
                ? ERROR
                : new Distance(this.meters - distance.meters);
    }

    public boolean isGreaterThan(final Distance distance) {
        return !this.error
                && !distance.error
                && this.meters > distance.meters;
    }

    @Override
    public String toString() {
        return this.error ? "ERROR"
                : format("%s meters", this.meters);
    }
}
