package fr.xebia.jqwik.exercise6;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;

import java.util.Set;

import static java.util.Collections.singleton;
import static net.jqwik.api.Arbitraries.defaultFor;
import static net.jqwik.api.Combinators.combine;

public final class ContractArbitraryProvider
        implements ArbitraryProvider {

    @Override
    public boolean canProvideFor(final TypeUsage targetType) {
        return targetType.isOfType(Contract.class);
    }

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
        return singleton(
                combine(
                        defaultFor(PolicyNumber.class),
                        defaultFor(ValiditySpan.class),
                        defaultFor(VehicleType.class).set().ofMaxSize(20),
                        defaultFor(Country.class).set()
                ).as((policyNumber, validitySpan, vehicleTypes, countries) ->
                        Contract.builder()
                                .policyNumber(policyNumber)
                                .validitySpan(validitySpan)
                                .coveredVehicleTypes(vehicleTypes)
                                .coveredCountries(countries)
                                .build()
                )
        );
    }

}
