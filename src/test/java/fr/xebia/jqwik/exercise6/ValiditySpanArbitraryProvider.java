package fr.xebia.jqwik.exercise6;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;

import java.time.Instant;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.*;
import static net.jqwik.api.Arbitraries.defaultFor;
import static net.jqwik.api.Combinators.combine;

public final class ValiditySpanArbitraryProvider
        implements ArbitraryProvider {

    @Override
    public boolean canProvideFor(final TypeUsage targetType) {
        return targetType.isOfType(ValiditySpan.class);
    }

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
        return singleton(
                combine(
                        defaultFor(Instant.class),
                        defaultFor(Instant.class)
                ).as((date1, date2) ->
                        ValiditySpan.between(min(asList(date1, date2)))
                                .and(max(asList(date1, date2)))
                )
        );
    }

}
