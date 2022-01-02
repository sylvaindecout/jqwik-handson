package fr.xebia.jqwik.exercise8;

import fr.xebia.jqwik.exercise8.geo.Coordinates;

import static java.util.Objects.requireNonNull;

record Location(String name, Coordinates coordinates) {

    Location {
        requireNonNull(name);
        requireNonNull(coordinates);
    }

}
