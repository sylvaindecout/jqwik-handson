package fr.xebia.jqwik.exercise6;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;

import java.time.Instant;
import java.util.Set;

import static java.util.Collections.singleton;
import static net.jqwik.api.Arbitraries.*;
import static net.jqwik.api.Combinators.withBuilder;

public final class IncidentArbitraryProvider
        implements ArbitraryProvider {

    @Override
    public boolean canProvideFor(final TypeUsage targetType) {
        return targetType.isOfType(Incident.class);
    }

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
        return singleton(
                withBuilder(Incident::builder)
                        .use(strings()).in(Incident.IncidentBuilder::id)
                        .use(of(Incident.Type.class)).in(Incident.IncidentBuilder::type)
                        .use(defaultFor(Instant.class)).in(Incident.IncidentBuilder::date)
                        .use(defaultFor(Country.class)).in(Incident.IncidentBuilder::country)
                        .use(defaultFor(Vehicle.class)).in(Incident.IncidentBuilder::vehicle)
                        .build(Incident.IncidentBuilder::build)
        );
    }

}
