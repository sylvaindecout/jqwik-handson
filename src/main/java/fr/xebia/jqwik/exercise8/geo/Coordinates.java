package fr.xebia.jqwik.exercise8.geo;

import com.google.common.collect.Range;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Math.*;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public record Coordinates(Coordinates.Latitude latitude,
                          Coordinates.Longitude longitude) {

    private static final double EARTH_RADIUS = 6371.01;

    public Coordinates {
        requireNonNull(latitude);
        requireNonNull(longitude);
    }

    public static Coordinates fromDegrees(final double lat, final double lng) {
        return new Coordinates(Latitude.fromDegrees(lat), Longitude.fromDegrees(lng));
    }

    public Distance distanceTo(final Coordinates coordinates) {
        final var latDistance = coordinates.latitude.asRadians() - this.latitude.asRadians();
        final var lngDistance = coordinates.longitude.asRadians() - this.longitude.asRadians();
        final var a = sin(latDistance / 2) * sin(latDistance / 2)
                + cos(this.latitude.asRadians()) * cos(coordinates.latitude.asRadians())
                * sin(lngDistance / 2) * sin(lngDistance / 2);
        final var c = 2 * atan2(sqrt(a), sqrt(1 - a));
        final var distanceMeters = EARTH_RADIUS * c;
        return Distance.fromKilometers(distanceMeters);
    }

    public record Latitude(double asDegrees, double asRadians) {

        private static final Range<Double> VALID_RANGE = Range.closed(0., 90.);

        static Latitude fromDegrees(final double degrees) {
            checkArgument(VALID_RANGE.contains(degrees),
                    "Latitude must be in %s° (input value: %s°)", VALID_RANGE, degrees);
            return new Latitude(degrees, toRadians(degrees));
        }

        @Override
        public String toString() {
            return format("%s°", this.asDegrees());
        }
    }

    public record Longitude(double asDegrees, double asRadians) {

        private static final Range<Double> VALID_RANGE = Range.openClosed(-180., 180.);

        static Longitude fromDegrees(final double degrees) {
            checkArgument(VALID_RANGE.contains(degrees),
                    "Longitude must be in %s° (input value: %s°)", VALID_RANGE, degrees);
            return new Longitude(degrees, toRadians(degrees));
        }

        @Override
        public String toString() {
            return format("%s°", this.asDegrees());
        }
    }
}
