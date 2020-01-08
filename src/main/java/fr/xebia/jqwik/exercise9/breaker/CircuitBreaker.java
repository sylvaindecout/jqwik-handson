package fr.xebia.jqwik.exercise9.breaker;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkState;
import static fr.xebia.jqwik.exercise9.breaker.CircuitBreaker.Status.*;

public class CircuitBreaker {

    public enum Status {OPEN, HALF_OPEN, CLOSED}

    private Status status;
    private int failuresCount;

    private final int failuresThreshold;

    private CircuitBreaker(final Status status, final int failuresCount, final int failuresThreshold) {
        this.status = status;
        this.failuresCount = failuresCount;
        this.failuresThreshold = failuresThreshold;
    }

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

    public Status getStatus() {
        return status;
    }

    public int getFailuresCount() {
        return failuresCount;
    }

    public int getFailuresThreshold() {
        return failuresThreshold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CircuitBreaker that = (CircuitBreaker) o;
        return failuresCount == that.failuresCount &&
                failuresThreshold == that.failuresThreshold &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, failuresCount, failuresThreshold);
    }

    @Override
    public String toString() {
        return "CircuitBreaker{" +
                "status=" + status +
                ", failuresCount=" + failuresCount +
                ", failuresThreshold=" + failuresThreshold +
                '}';
    }
}
