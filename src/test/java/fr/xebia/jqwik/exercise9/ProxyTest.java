package fr.xebia.jqwik.exercise9;

import fr.xebia.jqwik.exercise9.breaker.CircuitBreaker;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static fr.xebia.jqwik.exercise9.ServiceResult.callFailed;
import static fr.xebia.jqwik.exercise9.ServiceResult.serviceResponse;
import static fr.xebia.jqwik.exercise9.breaker.CircuitBreaker.Status.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class ProxyTest {

    private final RemoteService remoteService = mock(RemoteService.class);
    private final CircuitBreaker circuitBreaker = mock(CircuitBreaker.class);
    private final Proxy proxy = new Proxy(remoteService, circuitBreaker);

    @Property
    void should_return_result_from_service_if_circuit_is_closed(@ForAll ServiceParameters parameters) {
        reset(circuitBreaker);
        given(circuitBreaker.getStatus()).willReturn(CLOSED);
        given(remoteService.call(parameters)).willReturn(serviceResponse("result"));

        final ServiceResult result = proxy.callService(parameters);

        assertThat(result).isEqualTo(serviceResponse("result"));
        then(circuitBreaker).should().onSuccess();
    }

    @Property
    void should_return_result_from_service_if_circuit_is_half_open(@ForAll ServiceParameters parameters) {
        reset(circuitBreaker);
        given(circuitBreaker.getStatus()).willReturn(HALF_OPEN);
        given(remoteService.call(parameters)).willReturn(serviceResponse("result"));

        final ServiceResult result = proxy.callService(parameters);

        assertThat(result).isEqualTo(serviceResponse("result"));
        then(circuitBreaker).should().onSuccess();
    }

    @Property
    void should_return_failure_if_circuit_is_open(@ForAll ServiceParameters parameters) {
        reset(circuitBreaker);
        given(circuitBreaker.getStatus()).willReturn(OPEN);
        given(remoteService.call(parameters)).willReturn(serviceResponse("result"));

        final ServiceResult result = proxy.callService(parameters);

        assertThat(result).isEqualTo(callFailed("Circuit open"));
        then(circuitBreaker).should(never()).onFailure();
    }

    @Property
    void should_return_failure_if_circuit_is_closed_and_service_is_not_accessible(@ForAll ServiceParameters parameters) {
        reset(circuitBreaker);
        given(circuitBreaker.getStatus()).willReturn(CLOSED);
        given(remoteService.call(parameters)).willReturn(callFailed("unreachable"));

        final ServiceResult result = proxy.callService(parameters);

        assertThat(result).isEqualTo(callFailed("unreachable"));
        then(circuitBreaker).should().onFailure();
    }

    @Property
    void should_return_failure_if_circuit_is_half_open_and_service_is_not_accessible(@ForAll ServiceParameters parameters) {
        reset(circuitBreaker);
        given(circuitBreaker.getStatus()).willReturn(HALF_OPEN);
        given(remoteService.call(parameters)).willReturn(callFailed("unreachable"));

        final ServiceResult result = proxy.callService(parameters);

        assertThat(result).isEqualTo(callFailed("unreachable"));
        then(circuitBreaker).should().onFailure();
    }

}
