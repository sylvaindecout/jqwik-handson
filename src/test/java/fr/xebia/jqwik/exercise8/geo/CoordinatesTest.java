package fr.xebia.jqwik.exercise8.geo;

import fr.xebia.jqwik.exercise8.geo.Coordinates.Latitude;
import fr.xebia.jqwik.exercise8.geo.Coordinates.Longitude;
import net.jqwik.api.ForAll;
import net.jqwik.api.Group;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;
import net.jqwik.api.constraints.Negative;
import org.junit.jupiter.api.Test;

import static java.lang.Math.toRadians;
import static org.assertj.core.api.Assertions.*;

class CoordinatesTest {

    private static final Coordinates CHATEAUDUN = Coordinates.fromDegrees(48.08333, 1.33333);
    private static final Coordinates TOURS = Coordinates.fromDegrees(47.38333, 0.68333);
    private static final Coordinates ULM = Coordinates.fromDegrees(48.39841, 9.99155);
    private static final Coordinates BREMEN = Coordinates.fromDegrees(53.07516, 8.80777);

    @Group
    class Initialization {

        @Property
        void should_fail_to_initialize_from_null_latitude(@ForAll Longitude lng) {
            assertThatNullPointerException()
                    .isThrownBy(() -> new Coordinates(null, lng));
        }

        @Property
        void should_fail_to_initialize_from_null_latitude(@ForAll Latitude lat) {
            assertThatNullPointerException()
                    .isThrownBy(() -> new Coordinates(lat, null));
        }

        @Property
        void should_expose_latitude(@ForAll Latitude lat, @ForAll Longitude lng) {
            assertThat(new Coordinates(lat, lng).getLatitude()).isEqualTo(lat);
        }

        @Property
        void should_expose_longitude(@ForAll Latitude lat, @ForAll Longitude lng) {
            assertThat(new Coordinates(lat, lng).getLongitude()).isEqualTo(lng);
        }

        @Property
        void should_initialize_from_values_in_degrees(@ForAll Latitude lat, @ForAll Longitude lng) {
            assertThat(Coordinates.fromDegrees(lat.asDegrees(), lng.asDegrees()))
                    .isEqualTo(new Coordinates(lat, lng));
        }
    }

    /**
     * TODO #2: Test distance resolution as well as possible by relying on Jqwik without duplicating the solution into the test.
     * <br/>
     * <p>Hint #1: <a href="https://jqwik.net/docs/current/user-guide.html#creating-an-example-based-test">Creating an Example-based Test</a></p>
     * <p>Hint #2: Consider the properties of the operation (does it work for all sets of coordinates, is it commutative, etc.)</p>
     */
    @Test
    void should_compute_distance_between_Chateaudun_and_Tours() {
        assertThat(CHATEAUDUN.distanceTo(TOURS)).isEqualTo(Distance.fromMeters(91_768));
    }

    @Test
    void should_compute_distance_between_Ulm_and_Bremen() {
        assertThat(ULM.distanceTo(BREMEN)).isEqualTo(Distance.fromMeters(526_641));
    }

    @Group
    class LatitudeTest {

        @Property
        void should_expose_value_in_degrees(@ForAll @DoubleRange(min = 0, max = 90) double value) {
            assertThat(Latitude.fromDegrees(value).asDegrees()).isEqualTo(value);
        }

        @Property
        void should_expose_value_in_radians(@ForAll @DoubleRange(min = 0, max = 90) double value) {
            assertThat(Latitude.fromDegrees(value).asRadians()).isEqualTo(toRadians(value));
        }

        @Property
        void should_fail_to_initialize_from_value_below_0(@ForAll @Negative double value) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Latitude.fromDegrees(value))
                    .withMessage("Latitude must be in [0.0..90.0]° (input value: %s°)", value);
        }

        @Property
        void should_fail_to_initialize_from_value_above_90(@ForAll @DoubleRange(min = 90.001) double value) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Latitude.fromDegrees(value))
                    .withMessage("Latitude must be in [0.0..90.0]° (input value: %s°)", value);
        }

        @Property
        void should_display_distance_as_meters(@ForAll Latitude latitude) {
            assertThat(latitude.toString())
                    .isEqualTo("%s°", latitude.asDegrees());
        }
    }

    @Group
    class LongitudeTest {

        @Property
        void should_expose_value_in_degrees(@ForAll @DoubleRange(min = -179.999, max = 180) double value) {
            assertThat(Longitude.fromDegrees(value).asDegrees()).isEqualTo(value);
        }

        @Property
        void should_expose_value_in_radians(@ForAll @DoubleRange(min = -179.999, max = 180) double value) {
            assertThat(Longitude.fromDegrees(value).asRadians()).isEqualTo(toRadians(value));
        }

        @Property
        void should_fail_to_initialize_from_value_below_0(@ForAll @DoubleRange(min = -Double.MAX_VALUE, max = -180) double value) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Longitude.fromDegrees(value))
                    .withMessage("Longitude must be in (-180.0..180.0]° (input value: %s°)", value);
        }

        @Property
        void should_fail_to_initialize_from_value_above_90(@ForAll @DoubleRange(min = 180.001) double value) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Longitude.fromDegrees(value))
                    .withMessage("Longitude must be in (-180.0..180.0]° (input value: %s°)", value);
        }

        @Property
        void should_display_distance_as_meters(@ForAll Longitude longitude) {
            assertThat(longitude.toString())
                    .isEqualTo("%s°", longitude.asDegrees());
        }
    }
}
