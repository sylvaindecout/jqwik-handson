package fr.xebia.jqwik.exercise7;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO: Replace Example-Based Testing with Property-Based Testing and make test execution time acceptable.
 * <hr/>
 * <p>Hint: Have a look at what you can configure for any given property. Cf. <a href="https://jqwik.net/docs/current/user-guide.html#optional-property-parameters">Optional property parameters</a></p>
 */
class LongProcessTest {

    private final LongProcess longProcess = new LongProcess();

    @Property(tries = 1)
    void should_yield_input_parameter(@ForAll String parameter) throws InterruptedException {
        assertThat(longProcess.thisProcessIsSoLongYouWouldHardlyBelieveIt(parameter))
                .isEqualTo(parameter);
    }

}
