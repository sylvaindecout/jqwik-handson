package fr.xebia.jqwik.exercise8;

import lombok.NonNull;
import lombok.Value;

import static java.lang.Math.*;

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
        final double distanceMeters = EARTH_RADIUS * c * 1_000;
        return Distance.fromMeters(Double.valueOf(distanceMeters).longValue());
    }

}
