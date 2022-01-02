package fr.xebia.jqwik.exercise8;

import static java.util.Objects.requireNonNull;

record Incident(IncidentType type, Location location) {

    Incident {
        requireNonNull(type);
        requireNonNull(location);
    }

}
