package fr.xebia.jqwik.exercise5;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.Tuple;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;

import java.util.Set;

import static java.util.Collections.singleton;
import static net.jqwik.api.Arbitraries.frequency;

public final class CountryArbitraryProvider
        implements ArbitraryProvider {

    @Override
    public boolean canProvideFor(final TypeUsage targetType) {
        return targetType.isOfType(Country.class);
    }

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
        return singleton(
                // Note: This is a trick to make your tests fail more often in case you forget to exclude countries with specific services
                // Basically, you will get 30% US, 30% IT, 5% JP, 5% CN
                frequency(
                        Tuple.of(30, new Country("IT")),
                        Tuple.of(30, new Country("US")),
                        Tuple.of(5, new Country("JP")),
                        Tuple.of(5, new Country("CN"))
                )
        );
    }

}
