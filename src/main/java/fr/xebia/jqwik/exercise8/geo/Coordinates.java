package fr.xebia.jqwik.exercise8.geo;

import com.google.common.collect.Range;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Math.*;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public final class Coordinates {

    private static final double EARTH_RADIUS = 6371.01;

    private final Latitude latitude;
    private final Longitude longitude;

    Coordinates(final Latitude latitude, final Longitude longitude) {
        this.latitude = requireNonNull(latitude);
        this.longitude = requireNonNull(longitude);
    }

    public static Coordinates fromDegrees(final double lat, final double lng) {
        return new Coordinates(Latitude.fromDegrees(lat), Longitude.fromDegrees(lng));
    }

    public Distance distanceTo(final Coordinates coordinates) {
        final double latDistance = coordinates.latitude.asRadians() - this.latitude.asRadians();
        final double lngDistance = coordinates.longitude.asRadians() - this.longitude.asRadians();
        final double a = sin(latDistance / 2) * sin(latDistance / 2)
                + cos(this.latitude.asRadians()) * cos(coordinates.latitude.asRadians())
                * sin(lngDistance / 2) * sin(lngDistance / 2);
        final double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        final double distanceMeters = EARTH_RADIUS * c;
        return Distance.fromKilometers(distanceMeters);
    }

    public Latitude getLatitude() {
        return latitude;
    }

    public Longitude getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public static final class Latitude {

        private static final Range<Double> VALID_RANGE = Range.closed(0., 90.);

        private final double degrees;
        private final double radians;

        private Latitude(final double degrees, final double radians) {
            this.degrees = degrees;
            this.radians = radians;
        }

        static Latitude fromDegrees(final double degrees) {
            checkArgument(VALID_RANGE.contains(degrees),
                    "Latitude must be in %s° (input value: %s°)", VALID_RANGE, degrees);
            return new Latitude(degrees, toRadians(degrees));
        }

        public double asDegrees() {
            return this.degrees;
        }

        double asRadians() {
            return this.radians;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Latitude latitude = (Latitude) o;
            return Double.compare(latitude.degrees, degrees) == 0 &&
                    Double.compare(latitude.radians, radians) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(degrees, radians);
        }

        @Override
        public String toString() {
            return format("%s°", this.asDegrees());
        }
    }

    public static final class Longitude {

        private static final Range<Double> VALID_RANGE = Range.openClosed(-180., 180.);

        private final double degrees;
        private final double radians;

        private Longitude(final double degrees, final double radians) {
            this.degrees = degrees;
            this.radians = radians;
        }

        static Longitude fromDegrees(final double degrees) {
            checkArgument(VALID_RANGE.contains(degrees),
                    "Longitude must be in %s° (input value: %s°)", VALID_RANGE, degrees);
            return new Longitude(degrees, toRadians(degrees));
        }

        public double asDegrees() {
            return this.degrees;
        }

        double asRadians() {
            return this.radians;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Longitude longitude = (Longitude) o;
            return Double.compare(longitude.degrees, degrees) == 0 &&
                    Double.compare(longitude.radians, radians) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(degrees, radians);
        }

        @Override
        public String toString() {
            return format("%s°", this.asDegrees());
        }
    }
}
