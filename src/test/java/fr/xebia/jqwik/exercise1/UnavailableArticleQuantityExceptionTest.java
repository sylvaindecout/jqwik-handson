package fr.xebia.jqwik.exercise1;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

/**
 * TODO: Replace Example-Based Testing with Property-Based Testing.
 * <hr/>
 * <p>Hint: <a href="https://jqwik.net/docs/current/user-guide.html#creating-a-property">Creating a property</a></p>
 */
class UnavailableArticleQuantityExceptionTest {

    @Property
    void should_initialize_exception_with_explicit_message(@ForAll String requestedArticle, @ForAll int requestedQuantity, @ForAll int availableQuantity) {
        final var exception = new UnavailableArticleQuantityException(requestedArticle, requestedQuantity, availableQuantity);
        assertThat(exception)
                .hasMessage("Requested quantity (%s) is unavailable for article '%s' (available: %s)",
                        requestedQuantity, requestedArticle, availableQuantity);
    }

    @Property
    void should_fail_to_initialize_exception_if_requested_article_is_missing(@ForAll int requestedQuantity, @ForAll int availableQuantity) {
        assertThatNullPointerException().isThrownBy(() ->
                new UnavailableArticleQuantityException(null, requestedQuantity, availableQuantity));
    }

    @Property
    void should_fail_to_initialize_exception_if_requested_quantity_is_missing(@ForAll String requestedArticle, @ForAll int availableQuantity) {
        assertThatNullPointerException().isThrownBy(() ->
                new UnavailableArticleQuantityException(requestedArticle, null, availableQuantity));
    }

    @Property
    void should_fail_to_initialize_exception_if_available_quantity_is_missing(@ForAll String requestedArticle, @ForAll int requestedQuantity) {
        assertThatNullPointerException().isThrownBy(() ->
                new UnavailableArticleQuantityException(requestedArticle, requestedQuantity, null));
    }

}
