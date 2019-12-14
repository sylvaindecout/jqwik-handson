package fr.xebia.jqwik.exercise5;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;

import java.util.Set;

import static java.util.Collections.singleton;
import static net.jqwik.api.Arbitraries.defaultFor;
import static net.jqwik.api.Arbitraries.of;
import static net.jqwik.api.Combinators.combine;

public final class AlertArbitraryProvider
        implements ArbitraryProvider {

    @Override
    public boolean canProvideFor(final TypeUsage targetType) {
        return targetType.isOfType(Alert.class);
    }

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
        return singleton(
                combine(
                        of(Alert.Type.class),
                        defaultFor(Country.class)
                ).as(Alert::new)
        );
    }

}
