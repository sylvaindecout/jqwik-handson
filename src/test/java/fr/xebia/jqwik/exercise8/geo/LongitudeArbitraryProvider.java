package fr.xebia.jqwik.exercise8.geo;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;

import java.util.Set;

import static java.util.Collections.singleton;
import static net.jqwik.api.Arbitraries.doubles;

public final class LongitudeArbitraryProvider
        implements ArbitraryProvider {

    @Override
    public boolean canProvideFor(final TypeUsage targetType) {
        return targetType.isOfType(Coordinates.Longitude.class);
    }

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
        return singleton(
                doubles()
                        .between(-180, 180)
                        .filter(value -> value.compareTo(-180.) > 0)
                        .map(Coordinates.Longitude::fromDegrees)
        );
    }
}
