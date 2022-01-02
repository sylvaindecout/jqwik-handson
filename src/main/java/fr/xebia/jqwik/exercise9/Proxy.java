package fr.xebia.jqwik.exercise9;

import fr.xebia.jqwik.exercise9.breaker.CircuitBreaker;

import static fr.xebia.jqwik.exercise9.ServiceResult.callFailed;
import static fr.xebia.jqwik.exercise9.breaker.CircuitBreaker.Status.OPEN;

final class Proxy {

    private final RemoteService remoteService;
    private final CircuitBreaker circuitBreaker;

    Proxy(final RemoteService remoteService, final CircuitBreaker circuitBreaker) {
        this.remoteService = remoteService;
        this.circuitBreaker = circuitBreaker;
    }

    ServiceResult callService(final ServiceParameters parameters) {
        if (circuitBreaker.getStatus() == OPEN) {
            return callFailed("Circuit open");
        }
        final var result = remoteService.call(parameters);
        if (result.isFailure()) {
            circuitBreaker.onFailure();
        } else {
            circuitBreaker.onSuccess();
        }
        return result;
    }

}
