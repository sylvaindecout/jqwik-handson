package fr.xebia.jqwik.exercise8;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Map;

import static fr.xebia.jqwik.exercise8.Distance.ZERO;

@Value
@Builder
final class Contract {

    @Singular Map<IncidentType, Distance> limits;

    Distance getLimit(final IncidentType incidentType) {
        return limits.getOrDefault(incidentType, ZERO);
    }
}
