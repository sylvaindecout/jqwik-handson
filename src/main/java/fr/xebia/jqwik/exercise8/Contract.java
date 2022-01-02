package fr.xebia.jqwik.exercise8;

import fr.xebia.jqwik.exercise8.geo.Distance;

import java.util.HashMap;
import java.util.Map;

import static fr.xebia.jqwik.exercise8.geo.Distance.ZERO;

record Contract(Map<IncidentType, Distance> limits) {

    Distance getLimit(final IncidentType incidentType) {
        return limits.getOrDefault(incidentType, ZERO);
    }

    static ContractBuilder builder() {
        return new ContractBuilder();
    }

    static final class ContractBuilder {
        private final Map<IncidentType, Distance> limits = new HashMap<>();

        private ContractBuilder() {
        }

        ContractBuilder limit(final IncidentType incidentType, final Distance distance) {
            this.limits.put(incidentType, distance);
            return this;
        }

        Contract build() {
            return new Contract(limits);
        }
    }
}
