package fr.xebia.jqwik.exercise8;

import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static fr.xebia.jqwik.exercise8.Distance.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

class ContractTest {

    @Property
    void should_expose_limit_as_zero_by_default(@ForAll Contract contract, @ForAll IncidentType incidentType) {
        Assume.that(! contract.getLimits().containsKey(incidentType));
        assertThat(contract.getLimit(incidentType)).isEqualTo(ZERO);
    }

    @Property
    void should_expose_limit(@ForAll IncidentType incidentType, @ForAll Distance distance) {
        final Contract contract = Contract.builder()
                .limit(incidentType, distance)
                .build();
        assertThat(contract.getLimit(incidentType)).isEqualTo(distance);
    }
}
