package fr.xebia.jqwik.exercise9.breaker;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.Positive;
import net.jqwik.api.stateful.ActionSequence;

import static fr.xebia.jqwik.exercise9.breaker.CircuitBreaker.Status.CLOSED;
import static net.jqwik.api.Arbitraries.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO: Set up Property-Based Testing for the circuit breaker.
 * <hr/>
 * <p>Hint: Jqwik offers a mechanism dedicated to test state machines. Cf. <a href="https://jqwik.net/docs/current/user-guide.html#stateful-testing">Stateful testing</a></p>
 */
class CircuitBreakerProperties {

    @Property
    void checkTransitions(@ForAll("actions") ActionSequence<CircuitBreaker> actions,
                          @ForAll @Positive int threshold) {
        actions
                .withInvariant(breaker -> assertThat(breaker.getStatus() == CLOSED && breaker.getFailuresCount() > threshold).isFalse())
                .withInvariant(breaker -> assertThat(breaker.getStatus() != CLOSED && breaker.getFailuresCount() <= threshold).isFalse())
                .run(CircuitBreaker.withThreshold(threshold));
    }

    @Provide
    private static Arbitrary<ActionSequence<CircuitBreaker>> actions() {
        return sequences(oneOf(
                just(new SuccessAction()),
                just(new FailureAction()),
                just(new ResetTimeoutAction()),
                just(new UnexpectedSuccessAction()),
                just(new UnexpectedFailureAction()),
                just(new UnexpectedResetTimeoutAction())
        ));
    }
}
