package fr.xebia.jqwik.exercise8;

import fr.xebia.jqwik.exercise8.geo.Coordinates;
import fr.xebia.jqwik.exercise8.geo.Distance;
import org.junit.jupiter.api.Test;

import static fr.xebia.jqwik.exercise8.IncidentType.ACCIDENT;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO #1/2: Replace Example-Based Testing with Property-Based Testing.
 * <br/>
 * Especially take some time to ponder how much of the tested code (eq. the solution) you should accept duplicating in the test.
 */
class ClaimTest {

    private static final Location TOUR_EIFFEL = new Location("Tour Eiffel", Coordinates.fromDegrees(48.85827, 2.29453));
    private static final Garage CHEZ_DEDE = new Garage("Chez Dédé", Coordinates.fromDegrees(48.29494, 6.94246));
    private static final Distance DISTANCE_FROM_TOUR_EIFFEL_TO_CHEZ_DEDE = Distance.fromMeters(347_573);

    @Test
    void should_resolve_overdistance_if_limit_is_undefined() {
        final var claim = Claim.builder()
                .incident(new Incident(ACCIDENT, TOUR_EIFFEL))
                .contract(Contract.builder()
                        .build())
                .build();

        final var actual = claim.resolveOverdistance(CHEZ_DEDE);

        assertThat(actual).contains(DISTANCE_FROM_TOUR_EIFFEL_TO_CHEZ_DEDE);
    }

    @Test
    void should_resolve_overdistance_if_garage_is_further_than_limit() {
        final var limit = Distance.fromMeters(100_000);
        final var claim = Claim.builder()
                .incident(new Incident(ACCIDENT, TOUR_EIFFEL))
                .contract(Contract.builder()
                        .limit(ACCIDENT, limit)
                        .build())
                .build();

        final var actual = claim.resolveOverdistance(CHEZ_DEDE);

        assertThat(actual).contains(DISTANCE_FROM_TOUR_EIFFEL_TO_CHEZ_DEDE.minus(limit));
    }

    @Test
    void should_resolve_no_overdistance_if_garage_is_closer_than_limit() {
        final var limit = Distance.fromMeters(500_000);
        final var claim = Claim.builder()
                .incident(new Incident(ACCIDENT, TOUR_EIFFEL))
                .contract(Contract.builder()
                        .limit(ACCIDENT, limit)
                        .build())
                .build();

        final var actual = claim.resolveOverdistance(CHEZ_DEDE);

        assertThat(actual).isEmpty();
    }

}
