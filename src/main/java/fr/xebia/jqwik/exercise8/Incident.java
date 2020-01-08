package fr.xebia.jqwik.exercise8;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

final class Incident {

    private final IncidentType type;
    private final Location location;

    Incident(final IncidentType type, final Location location) {
        this.type = requireNonNull(type);
        this.location = requireNonNull(location);
    }

    IncidentType getType() {
        return type;
    }

    Location getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incident incident = (Incident) o;
        return type == incident.type &&
                Objects.equals(location, incident.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, location);
    }

    @Override
    public String toString() {
        return "Incident{" +
                "type=" + type +
                ", location=" + location +
                '}';
    }
}
