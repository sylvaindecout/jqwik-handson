package fr.xebia.jqwik.exercise8;

import fr.xebia.jqwik.exercise8.geo.Coordinates;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

final class Location {

    private final String name;
    private final Coordinates coordinates;

    Location(final String name, final Coordinates coordinates) {
        this.name = requireNonNull(name);
        this.coordinates = requireNonNull(coordinates);
    }

    String getName() {
        return name;
    }

    Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(name, location.name) &&
                Objects.equals(coordinates, location.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates);
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
