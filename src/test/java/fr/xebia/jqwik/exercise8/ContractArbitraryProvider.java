package fr.xebia.jqwik.exercise8;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;

import java.util.Set;

import static java.util.Collections.singleton;
import static net.jqwik.api.Arbitraries.*;

public final class ContractArbitraryProvider
        implements ArbitraryProvider {

    @Override
    public boolean canProvideFor(final TypeUsage targetType) {
        return targetType.isOfType(Contract.class);
    }

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
        return singleton(
                maps(
                        of(IncidentType.class),
                        defaultFor(Distance.class)
                ).map(Contract::new)
        );
    }
}
