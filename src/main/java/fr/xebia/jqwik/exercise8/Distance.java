package fr.xebia.jqwik.exercise8;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@EqualsAndHashCode
@AllArgsConstructor(access = PRIVATE)
final class Distance {

    public static final Distance ZERO = new Distance(0, false);
    public static final Distance ERROR = new Distance(-1, true);

    private final long meters;
    private final boolean error;

    static Distance fromMeters(final long meters) {
        checkArgument(meters >= 0,
                "Distance must be positive (input value: %s)", meters);
        return new Distance(meters, false);
    }

    long asMeters() {
        return this.meters;
    }

    boolean isError() {
        return this.error;
    }

    Distance minus(final Distance distance) {
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
    public String toString() {
        return this.isError() ? "ERROR"
                : format("%s meters", this.asMeters());
    }
}
