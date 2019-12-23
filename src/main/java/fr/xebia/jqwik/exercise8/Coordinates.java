package fr.xebia.jqwik.exercise8;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Math.*;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Value
final class Coordinates {

    private static final double EARTH_RADIUS = 6371.01;

    @NonNull Latitude latitude;
    @NonNull Longitude longitude;

    static Coordinates fromDegrees(final double lat, final double lng) {
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

    @EqualsAndHashCode
    @AllArgsConstructor(access = PRIVATE)
    static final class Latitude {

        private static final Range<Double> VALID_RANGE = Range.closed(0., 90.);

        private final double degrees;
        private final double radians;

        static Latitude fromDegrees(final double degrees) {
            checkArgument(VALID_RANGE.contains(degrees),
                    "Latitude must be in %s° (input value: %s°)", VALID_RANGE, degrees);
            return new Latitude(degrees, toRadians(degrees));
        }

        double asDegrees() {
            return this.degrees;
        }

        double asRadians() {
            return this.radians;
        }

        @Override
        public String toString() {
            return format("%s°", this.asDegrees());
        }
    }

    @EqualsAndHashCode
    @AllArgsConstructor(access = PRIVATE)
    static final class Longitude {

        private static final Range<Double> VALID_RANGE = Range.openClosed(-180., 180.);

        private final double degrees;
        private final double radians;

        static Longitude fromDegrees(final double degrees) {
            checkArgument(VALID_RANGE.contains(degrees),
                    "Longitude must be in %s° (input value: %s°)", VALID_RANGE, degrees);
            return new Longitude(degrees, toRadians(degrees));
        }

        double asDegrees() {
            return this.degrees;
        }

        double asRadians() {
            return this.radians;
        }

        @Override
        public String toString() {
            return format("%s°", this.asDegrees());
        }
    }
}
