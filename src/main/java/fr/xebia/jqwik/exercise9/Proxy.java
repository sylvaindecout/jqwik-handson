package fr.xebia.jqwik.exercise9;

import fr.xebia.jqwik.exercise9.breaker.CircuitBreaker;
import lombok.AllArgsConstructor;

import static fr.xebia.jqwik.exercise9.breaker.CircuitBreaker.Status.OPEN;
import static fr.xebia.jqwik.exercise9.ServiceResult.callFailed;

@AllArgsConstructor
final class Proxy {

    private final RemoteService remoteService;
    private final CircuitBreaker circuitBreaker;

    ServiceResult callService(final ServiceParameters parameters) {
        if (circuitBreaker.getStatus() == OPEN) {
            return callFailed("Circuit open");
        }
        final ServiceResult result = remoteService.call(parameters);
        if (result.isFailure()) {
            circuitBreaker.onFailure();
        } else {
            circuitBreaker.onSuccess();
        }
        return result;
    }

}
