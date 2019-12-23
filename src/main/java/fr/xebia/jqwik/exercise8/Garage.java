package fr.xebia.jqwik.exercise8;

import fr.xebia.jqwik.exercise8.geo.Coordinates;
import lombok.NonNull;
import lombok.Value;

@Value
final class Garage {
    @NonNull String name;
    @NonNull Coordinates coordinates;
}
