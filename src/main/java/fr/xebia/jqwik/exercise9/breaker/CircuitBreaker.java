package fr.xebia.jqwik.exercise9.breaker;

import lombok.AllArgsConstructor;
import lombok.Data;

import static com.google.common.base.Preconditions.checkState;
import static fr.xebia.jqwik.exercise9.breaker.CircuitBreaker.Status.*;
import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
public class CircuitBreaker {

    public enum Status {OPEN, HALF_OPEN, CLOSED}

    Status status;
    int failuresCount;

    final int failuresThreshold;

    public static CircuitBreaker withThreshold(final int threshold) {
        return new CircuitBreaker(CLOSED, 0, threshold);
    }

    public void onFailure() {
        checkState(this.status != OPEN,
                "Former status is inconsistent (%s)", this.status);
        this.failuresCount = this.failuresCount + 1;
        if (this.status == HALF_OPEN || this.failuresCount > this.failuresThreshold) {
            this.status = OPEN;
        }
    }

    public void onSuccess() {
        checkState(this.status != OPEN,
                "Former status is inconsistent (%s)", this.status);
        this.status = CLOSED;
        this.failuresCount = 0;
    }

    public void onResetTimeout() {
        checkState(this.status == OPEN,
                "Former status is inconsistent (%s)", this.status);
        this.status = HALF_OPEN;
    }

}
