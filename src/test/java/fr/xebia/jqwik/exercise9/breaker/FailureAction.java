package fr.xebia.jqwik.exercise9.breaker;

import net.jqwik.api.stateful.Action;

import static fr.xebia.jqwik.exercise9.breaker.CircuitBreaker.Status.OPEN;
import static org.assertj.core.api.Assertions.assertThat;

final class FailureAction implements Action<CircuitBreaker> {

    @Override
    public boolean precondition(final CircuitBreaker breaker) {
        return breaker.getStatus() != OPEN;
    }

    @Override
    public CircuitBreaker run(final CircuitBreaker breaker) {
        final int formerFailuresCount = breaker.getFailuresCount();
        breaker.onFailure();
        assertThat(breaker.getFailuresCount()).isEqualTo(formerFailuresCount + 1);
        return breaker;
    }

    @Override
    public String toString() {
        return "failure";
    }
}
