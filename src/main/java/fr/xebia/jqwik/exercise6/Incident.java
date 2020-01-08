package fr.xebia.jqwik.exercise6;

import java.time.Instant;
import java.util.Objects;

final class Incident {

    private final String id;
    private final Instant date;
    private final Country country;
    private final Type type;
    private final Vehicle vehicle;

    private Incident(final String id, final Instant date, final Country country,
                     final Type type, final Vehicle vehicle) {
        this.id = id;
        this.date = date;
        this.country = country;
        this.type = type;
        this.vehicle = vehicle;
    }

    String getId() {
        return id;
    }

    Instant getDate() {
        return date;
    }

    Country getCountry() {
        return country;
    }

    Type getType() {
        return type;
    }

    Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incident incident = (Incident) o;
        return Objects.equals(id, incident.id) &&
                Objects.equals(date, incident.date) &&
                Objects.equals(country, incident.country) &&
                type == incident.type &&
                Objects.equals(vehicle, incident.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, country, type, vehicle);
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", country=" + country +
                ", type=" + type +
                ", vehicle=" + vehicle +
                '}';
    }

    enum Type {
        ACCIDENT, BREAKDOWN
    }

    static IncidentBuilder builder() {
        return new IncidentBuilder();
    }

    static final class IncidentBuilder {
        private String id;
        private Instant date;
        private Country country;
        private Type type;
        private Vehicle vehicle;

        private IncidentBuilder() {}

        IncidentBuilder id(String id) {
            this.id = id;
            return this;
        }

        IncidentBuilder date(Instant date) {
            this.date = date;
            return this;
        }

        IncidentBuilder country(Country country) {
            this.country = country;
            return this;
        }

        IncidentBuilder type(Type type) {
            this.type = type;
            return this;
        }

        IncidentBuilder vehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        Incident build() {
            return new Incident(id, date, country, type, vehicle);
        }
    }
}
