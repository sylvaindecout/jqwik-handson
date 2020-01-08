package fr.xebia.jqwik.exercise6;

import java.time.Instant;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

final class ValiditySpan {

    private final Instant start;
    private final Instant end;

    private ValiditySpan(final Instant start, final Instant end) {
        this.start = start;
        this.end = end;
    }

    boolean contains(final Instant instant) {
        requireNonNull(instant);
        return !instant.isBefore(this.start)
                && !instant.isAfter(this.end);
    }

    Instant getStart() {
        return start;
    }

    Instant getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValiditySpan that = (ValiditySpan) o;
        return Objects.equals(start, that.start) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "ValiditySpan{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    static WithStartDefined between(final Instant start) {
        return new Builder(start);
    }

    interface WithStartDefined {
        ValiditySpan and(Instant end);
    }

    private static final class Builder implements WithStartDefined {

        private final Instant start;

        Builder(final Instant start) {
            this.start = requireNonNull(start);
        }

        @Override
        public ValiditySpan and(final Instant end) {
            requireNonNull(end);
            checkArgument(!end.isBefore(this.start),
                    "Start date must be before end date");
            return new ValiditySpan(this.start, end);
        }
    }

}
