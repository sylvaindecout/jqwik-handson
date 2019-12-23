package fr.xebia.jqwik.exercise8;

import net.jqwik.api.ForAll;
import net.jqwik.api.Group;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

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

}
