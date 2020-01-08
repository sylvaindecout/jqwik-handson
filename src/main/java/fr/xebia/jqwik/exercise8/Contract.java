package fr.xebia.jqwik.exercise8;

import fr.xebia.jqwik.exercise8.geo.Distance;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static fr.xebia.jqwik.exercise8.geo.Distance.ZERO;

final class Contract {

    private final Map<IncidentType, Distance> limits;

    Contract(final Map<IncidentType, Distance> limits) {
        this.limits = limits;
    }

    Distance getLimit(final IncidentType incidentType) {
        return limits.getOrDefault(incidentType, ZERO);
    }

    Map<IncidentType, Distance> getLimits() {
        return limits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return Objects.equals(limits, contract.limits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(limits);
    }

    @Override
    public String toString() {
        return "Contract{" +
                "limits=" + limits +
                '}';
    }

    static ContractBuilder builder() {
        return new ContractBuilder();
    }

    static final class ContractBuilder {
        private final Map<IncidentType, Distance> limits = new HashMap<>();

        private ContractBuilder() {}

        ContractBuilder limit(final IncidentType incidentType, final Distance distance) {
            this.limits.put(incidentType, distance);
            return this;
        }

        Contract build() {
            return new Contract(limits);
        }
    }
}
