package fr.xebia.jqwik.exercise6;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

import static com.google.common.base.Preconditions.checkArgument;
import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
final class ValiditySpan {

    Instant start;
    Instant end;

    boolean contains(@NonNull final Instant instant) {
        return ! instant.isBefore(this.start)
                && ! instant.isAfter(this.end);
    }

    static WithStartDefined between(@NonNull final Instant start){
        return new Builder(start);
    }

    interface WithStartDefined {
        ValiditySpan and(Instant end);
    }

    @AllArgsConstructor
    private static final class Builder implements WithStartDefined {

        private final Instant start;

        @Override
        public ValiditySpan and(@NonNull final Instant end) {
            checkArgument(! end.isBefore(this.start),
                    "Start date must be before end date");
            return new ValiditySpan(this.start, end);
        }
    }

}
