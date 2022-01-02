package fr.xebia.jqwik.exercise6;

import java.time.Instant;

record Incident(String id, Instant date, Country country,
                Incident.Type type, Vehicle vehicle) {

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

        private IncidentBuilder() {
        }

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
