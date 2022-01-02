package fr.xebia.jqwik.exercise8;

import fr.xebia.jqwik.exercise8.geo.Coordinates;

import static java.util.Objects.requireNonNull;

record Garage(String name, Coordinates coordinates) {

    Garage {
        requireNonNull(name);
        requireNonNull(coordinates);
    }

}
