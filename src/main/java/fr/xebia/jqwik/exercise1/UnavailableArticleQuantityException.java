package fr.xebia.jqwik.exercise1;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

final class UnavailableArticleQuantityException extends RuntimeException {

    UnavailableArticleQuantityException(final String requestedArticle, final Integer requestedQuantity, final Integer availableQuantity) {
        super(format("Requested quantity (%s) is unavailable for article '%s' (available: %s)",
                requireNonNull(requestedQuantity),
                requireNonNull(requestedArticle),
                requireNonNull(availableQuantity)));
    }

}
