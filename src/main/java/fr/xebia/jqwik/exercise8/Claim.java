package fr.xebia.jqwik.exercise8;

import fr.xebia.jqwik.exercise8.geo.Distance;

import java.util.Optional;

record Claim(Incident incident, Contract contract) {

    Optional<Distance> resolveOverdistance(final Garage destination) {
        final var distance = incident.location().coordinates().distanceTo(destination.coordinates());
        final var limitDistance = contract.getLimit(incident.type());
        return distance.isGreaterThan(limitDistance)
                ? Optional.of(distance.minus(limitDistance))
                : Optional.empty();
    }

    static ClaimBuilder builder() {
        return new ClaimBuilder();
    }

    static final class ClaimBuilder {
        private Incident incident;
        private Contract contract;

        private ClaimBuilder() {
        }

        ClaimBuilder incident(Incident incident) {
            this.incident = incident;
            return this;
        }

        ClaimBuilder contract(Contract contract) {
            this.contract = contract;
            return this;
        }

        Claim build() {
            return new Claim(incident, contract);
        }
    }
}
