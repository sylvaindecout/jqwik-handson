package fr.xebia.jqwik.exercise1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

/**
 * TODO: Replace Example-Based Testing with Property-Based Testing.
 * <hr/>
 * <p>Hint: <a href="https://jqwik.net/docs/current/user-guide.html#creating-a-property">Creating a property</a></p>
 */
class UnavailableArticleQuantityExceptionTest {

    private static final String VALID_ARTICLE = "BOOK00001";
    private static final Integer VALID_QUANTITY = 13;

    @Test
    void should_initialize_exception_with_explicit_message() {
        final String requestedArticle = "BOOK00001";
        final int requestedQuantity = 12;
        final int availableQuantity = 2;
        final Exception exception = new UnavailableArticleQuantityException(requestedArticle, requestedQuantity, availableQuantity);
        assertThat(exception)
                .hasMessage("Requested quantity (%s) is unavailable for article '%s' (available: %s)",
                        requestedQuantity, requestedArticle, availableQuantity);
    }

    @Test
    void should_fail_to_initialize_exception_if_requested_article_is_missing() {
        assertThatNullPointerException().isThrownBy(() ->
                new UnavailableArticleQuantityException(null, VALID_QUANTITY, VALID_QUANTITY));
    }

    @Test
    void should_fail_to_initialize_exception_if_requested_quantity_is_missing() {
        assertThatNullPointerException().isThrownBy(() ->
                new UnavailableArticleQuantityException(VALID_ARTICLE, null, VALID_QUANTITY));
    }

    @Test
    void should_fail_to_initialize_exception_if_available_quantity_is_missing() {
        assertThatNullPointerException().isThrownBy(() ->
                new UnavailableArticleQuantityException(VALID_ARTICLE, VALID_QUANTITY, null));
    }

}
