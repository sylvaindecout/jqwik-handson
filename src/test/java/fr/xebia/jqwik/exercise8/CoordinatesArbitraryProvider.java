package fr.xebia.jqwik.exercise8;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;

import java.util.Set;

import static java.util.Collections.singleton;
import static net.jqwik.api.Arbitraries.defaultFor;
import static net.jqwik.api.Combinators.combine;

public final class CoordinatesArbitraryProvider
        implements ArbitraryProvider {

    @Override
    public boolean canProvideFor(final TypeUsage targetType) {
        return targetType.isOfType(Coordinates.class);
    }

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
        return singleton(
                combine(
                        defaultFor(Coordinates.Latitude.class),
                        defaultFor(Coordinates.Longitude.class)
                ).as(Coordinates::new)
        );
    }
}
