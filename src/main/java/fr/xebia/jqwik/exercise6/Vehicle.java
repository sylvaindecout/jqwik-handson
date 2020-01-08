package fr.xebia.jqwik.exercise6;

import java.util.Objects;

final class Vehicle {

    private final VehicleType type;
    private final String model;
    private final String plateNumber;
    private final Country country;

    Vehicle(final VehicleType type, final String model, final String plateNumber, final Country country) {
        this.type = type;
        this.model = model;
        this.plateNumber = plateNumber;
        this.country = country;
    }

    VehicleType getType() {
        return type;
    }

    String getModel() {
        return model;
    }

    String getPlateNumber() {
        return plateNumber;
    }

    Country getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(type, vehicle.type) &&
                Objects.equals(model, vehicle.model) &&
                Objects.equals(plateNumber, vehicle.plateNumber) &&
                Objects.equals(country, vehicle.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, model, plateNumber, country);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "type=" + type +
                ", model='" + model + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", country=" + country +
                '}';
    }
}
