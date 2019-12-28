package fr.xebia.jqwik.exercise9.breaker;

import net.jqwik.api.stateful.Action;

import static fr.xebia.jqwik.exercise9.breaker.CircuitBreaker.Status.OPEN;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

final class UnexpectedSuccessAction implements Action<CircuitBreaker> {

    @Override
    public boolean precondition(final CircuitBreaker breaker) {
        return breaker.getStatus() == OPEN;
    }

    @Override
    public CircuitBreaker run(final CircuitBreaker breaker) {
        assertThatIllegalStateException()
                .isThrownBy(breaker::onSuccess)
                .withMessage("Former status is inconsistent (%s)", breaker.getStatus());
        return breaker;
    }

    @Override
    public String toString() {
        return "success (unexpected)";
    }
}
