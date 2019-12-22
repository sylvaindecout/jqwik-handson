package fr.xebia.jqwik.exercise8;

import lombok.NonNull;
import lombok.Value;

@Value
final class Incident {
    @NonNull IncidentType type;
    @NonNull Location location;
}
