package fr.xebia.jqwik.exercise6;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;

import java.util.Set;

import static java.util.Collections.singleton;
import static net.jqwik.api.Arbitraries.*;
import static net.jqwik.api.Combinators.combine;

public final class VehicleArbitraryProvider
        implements ArbitraryProvider {

    @Override
    public boolean canProvideFor(final TypeUsage targetType) {
        return targetType.isOfType(Vehicle.class);
    }

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
        return singleton(
                combine(
                        defaultFor(VehicleType.class),
                        strings(),
                        strings(),
                        defaultFor(Country.class)
                ).as(Vehicle::new)
        );
    }

}
