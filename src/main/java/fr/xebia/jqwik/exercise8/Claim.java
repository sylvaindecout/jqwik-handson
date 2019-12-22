package fr.xebia.jqwik.exercise8;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

@Value
@Builder
final class Claim {

    Incident incident;
    Contract contract;

    Optional<Distance> resolveOverdistance(final Garage destination) {
        final Distance distance = incident.getLocation().getCoordinates().distanceTo(destination.getCoordinates());
        final Distance limitDistance = contract.getLimit(incident.getType());
        return distance.isGreaterThan(limitDistance)
                ? Optional.of(distance.minus(limitDistance))
                : Optional.empty();
    }
}
