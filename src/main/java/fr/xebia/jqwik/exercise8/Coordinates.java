package fr.xebia.jqwik.exercise8;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Math.*;
import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
final class Coordinates {

    private static final double EARTH_RADIUS = 6371.01;

    @NonNull Latitude latitude;
    @NonNull Longitude longitude;

    static Coordinates fromDegrees(final double lat, final double lng) {
        return new Coordinates(new Latitude(lat), new Longitude(lng));
    }

    public Distance distanceTo(final Coordinates coordinates) {
        final double latDistance = coordinates.latitude.radians - this.latitude.radians;
        final double lngDistance = coordinates.longitude.radians - this.longitude.radians;
        final double a = sin(latDistance / 2) * sin(latDistance / 2)
                + cos(this.latitude.radians) * cos(coordinates.latitude.radians)
                * sin(lngDistance / 2) * sin(lngDistance / 2);
        final double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        final double distanceMeters = EARTH_RADIUS * c * 1_000;
        return new Distance(Double.valueOf(distanceMeters).longValue());
    }

    @Value
    static final class Latitude {

        private static final Range<Double> VALID_RANGE = Range.closed(0., 90.);

        double degrees;
        double radians;

        Latitude(final double degrees) {
            checkArgument(VALID_RANGE.contains(degrees),
                    "Latitude must be in %s° (input value: %s°)", VALID_RANGE, degrees);
            this.degrees = degrees;
            this.radians = toRadians(degrees);
        }
    }

    @Value
    static final class Longitude {

        private static final Range<Double> VALID_RANGE = Range.openClosed(-180., 180.);

        double degrees;
        double radians;

        Longitude(final double degrees) {
            checkArgument(VALID_RANGE.contains(degrees),
                    "Longitude must be in %s° (input value: %s°)", VALID_RANGE, degrees);
            this.degrees = degrees;
            this.radians = toRadians(degrees);
        }
    }
}
