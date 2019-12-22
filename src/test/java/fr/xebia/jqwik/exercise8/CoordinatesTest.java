package fr.xebia.jqwik.exercise8;

import net.jqwik.api.ForAll;
import net.jqwik.api.Group;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class CoordinatesTest {

    private static final Coordinates CHATEAUDUN = Coordinates.fromDegrees(48.08333, 1.33333);
    private static final Coordinates TOURS = Coordinates.fromDegrees(47.38333, 0.68333);
    private static final Coordinates ULM = Coordinates.fromDegrees(48.39841, 9.99155);
    private static final Coordinates BREMEN = Coordinates.fromDegrees(53.07516, 8.80777);

    @Group
    class Initialization {

        @Property
        void should_fail_to_initialize_from_latitude_below_0_degrees(@ForAll @DoubleRange(min = -Double.MAX_VALUE, max = -0.001) double lat,
                                                                     @ForAll @DoubleRange(min = -180, max = 180) double lng) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Coordinates.fromDegrees(lat, lng))
                    .withMessage("Latitude must be in [0.0..90.0]° (input value: %s°)", lat);
        }

        @Property
        void should_fail_to_initialize_from_latitude_above_90_degrees(@ForAll @DoubleRange(min = 90.001) double lat,
                                                                      @ForAll @DoubleRange(min = -180, max = 180) double lng) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Coordinates.fromDegrees(lat, lng))
                    .withMessage("Latitude must be in [0.0..90.0]° (input value: %s°)", lat);
        }

        @Property
        void should_fail_to_initialize_from_longitude_below_minus_180_degrees(@ForAll @DoubleRange(min = 0, max = 90) double lat,
                                                                              @ForAll @DoubleRange(min = -Double.MAX_VALUE, max = -180) double lng) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Coordinates.fromDegrees(lat, lng))
                    .withMessage("Longitude must be in (-180.0..180.0]° (input value: %s°)", lng);
        }

        @Property
        void should_fail_to_initialize_from_longitude_above_180_degrees(@ForAll @DoubleRange(min = 0, max = 90) double lat,
                                                                        @ForAll @DoubleRange(min = 180.001) double lng) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Coordinates.fromDegrees(lat, lng))
                    .withMessage("Longitude must be in (-180.0..180.0]° (input value: %s°)", lng);
        }

        @Property
        void should_expose_latitude(@ForAll @DoubleRange(min = 0, max = 90) double lat,
                                    @ForAll @DoubleRange(min = -179.999, max = 180) double lng) {
            assertThat(Coordinates.fromDegrees(lat, lng).getLatitude())
                    .isEqualTo(new Coordinates.Latitude(lat));
        }

        @Property
        void should_expose_longitude(@ForAll @DoubleRange(min = 0, max = 90) double lat,
                                     @ForAll @DoubleRange(min = -179.999, max = 180) double lng) {
            assertThat(Coordinates.fromDegrees(lat, lng).getLongitude())
                    .isEqualTo(new Coordinates.Longitude(lng));
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
        assertThat(CHATEAUDUN.distanceTo(TOURS)).isEqualTo(new Distance(91_768));
    }

    @Test
    void should_compute_distance_between_Ulm_and_Bremen() {
        assertThat(ULM.distanceTo(BREMEN)).isEqualTo(new Distance(526_641));
    }

}
