package fr.xebia.jqwik.exercise5;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.configurators.ArbitraryConfiguratorBase;
import net.jqwik.api.providers.TypeUsage;

import java.util.Collection;

import static java.util.Arrays.asList;

public class AlertCountryConfigurator
        extends ArbitraryConfiguratorBase {

    private static final Collection<Country> COUNTRIES_WITH_SPECIFIC_SERVICES = asList(new Country("IT"), new Country("US"));

    @Override
    protected boolean acceptTargetType(final TypeUsage targetType) {
        return targetType.isAssignableFrom(Alert.class);
    }

    public Arbitrary<Alert> configure(final Arbitrary<Alert> arbitrary, final StandardCountry annotation) {
        return arbitrary
                .filter(alert -> !COUNTRIES_WITH_SPECIFIC_SERVICES.contains(alert.country()));
    }

}
