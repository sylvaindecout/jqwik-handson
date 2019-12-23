package fr.xebia.jqwik.exercise8;

import fr.xebia.jqwik.exercise8.geo.Distance;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Map;

import static fr.xebia.jqwik.exercise8.geo.Distance.ZERO;

@Value
@Builder
final class Contract {

    @Singular Map<IncidentType, Distance> limits;

    Distance getLimit(final IncidentType incidentType) {
        return limits.getOrDefault(incidentType, ZERO);
    }
}
