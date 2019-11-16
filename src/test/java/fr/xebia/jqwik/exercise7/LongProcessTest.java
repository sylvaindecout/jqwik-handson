package fr.xebia.jqwik.exercise7;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO: Replace Example-Based Testing with Property-Based Testing and make test execution time acceptable.
 * <br/>
 * <p>Hint: <a href="https://jqwik.net/docs/current/user-guide.html#optional-property-parameters">Optional property parameters</a></p>
 */
class LongProcessTest {

    private final LongProcess longProcess = new LongProcess();

    @Test
    void should_yield_input_parameter() throws InterruptedException {
        final String parameter = "The parameter";
        assertThat(longProcess.thisProcessIsSoLongYouWouldHardlyBelieveIt(parameter))
                .isEqualTo(parameter);
    }

}
