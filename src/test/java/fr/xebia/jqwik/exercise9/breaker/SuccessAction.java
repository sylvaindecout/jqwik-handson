package fr.xebia.jqwik.exercise9.breaker;

import net.jqwik.api.stateful.Action;

import static fr.xebia.jqwik.exercise9.breaker.CircuitBreaker.Status.CLOSED;
import static fr.xebia.jqwik.exercise9.breaker.CircuitBreaker.Status.OPEN;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

final class SuccessAction implements Action<CircuitBreaker> {

    @Override
    public boolean precondition(final CircuitBreaker breaker) {
        return breaker.getStatus() != OPEN;
    }

    @Override
    public CircuitBreaker run(final CircuitBreaker breaker) {
        breaker.onSuccess();
        assertSoftly(softly -> {
            softly.assertThat(breaker.getStatus()).isEqualTo(CLOSED);
            softly.assertThat(breaker.getFailuresCount()).isZero();
        });
        return breaker;
    }

    @Override
    public String toString() {
        return "success";
    }
}
