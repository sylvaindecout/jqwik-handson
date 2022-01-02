package fr.xebia.jqwik.exercise8;

import fr.xebia.jqwik.exercise8.geo.Distance;

import java.util.Objects;
import java.util.Optional;

final class Claim {

    private final Incident incident;
    private final Contract contract;

    private Claim(final Incident incident, final Contract contract) {
        this.incident = incident;
        this.contract = contract;
    }

    Optional<Distance> resolveOverdistance(final Garage destination) {
        final var distance = incident.getLocation().getCoordinates().distanceTo(destination.getCoordinates());
        final var limitDistance = contract.getLimit(incident.getType());
        return distance.isGreaterThan(limitDistance)
                ? Optional.of(distance.minus(limitDistance))
                : Optional.empty();
    }

    Incident getIncident() {
        return incident;
    }

    Contract getContract() {
        return contract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Claim claim = (Claim) o;
        return Objects.equals(incident, claim.incident) &&
                Objects.equals(contract, claim.contract);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incident, contract);
    }

    @Override
    public String toString() {
        return "Claim{" +
                "incident=" + incident +
                ", contract=" + contract +
                '}';
    }

    static ClaimBuilder builder() {
        return new ClaimBuilder();
    }

    static final class ClaimBuilder {
        private Incident incident;
        private Contract contract;

        private ClaimBuilder() {}

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
