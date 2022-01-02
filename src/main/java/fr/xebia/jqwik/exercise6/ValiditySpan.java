package fr.xebia.jqwik.exercise6;

import java.time.Instant;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

record ValiditySpan(Instant start, Instant end) {

    boolean contains(final Instant instant) {
        requireNonNull(instant);
        return !instant.isBefore(this.start)
                && !instant.isAfter(this.end);
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
