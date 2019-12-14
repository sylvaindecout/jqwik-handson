package fr.xebia.jqwik.exercise6;

import lombok.Value;

@Value
final class Vehicle {
    VehicleType type;
    String model;
    String plateNumber;
    Country country;
}
