package fr.xebia.jqwik.exercise9.breaker;

import net.jqwik.api.stateful.Action;

import static fr.xebia.jqwik.exercise9.breaker.CircuitBreaker.Status.HALF_OPEN;
import static fr.xebia.jqwik.exercise9.breaker.CircuitBreaker.Status.OPEN;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

final class ResetTimeoutAction implements Action<CircuitBreaker> {

    @Override
    public boolean precondition(final CircuitBreaker breaker) {
        return breaker.getStatus() == OPEN;
    }

    @Override
    public CircuitBreaker run(final CircuitBreaker breaker) {
        breaker.onResetTimeout();
        assertSoftly(softly -> {
            softly.assertThat(breaker.getStatus()).isEqualTo(HALF_OPEN);
            softly.assertThat(breaker.getFailuresCount()).isEqualTo(breaker.getFailuresCount());
        });
        return breaker;
    }

    @Override
    public String toString() {
        return "reset timeout";
    }
}
