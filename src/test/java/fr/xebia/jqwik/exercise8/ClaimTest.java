package fr.xebia.jqwik.exercise8;

import fr.xebia.jqwik.exercise8.geo.Distance;
import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO #1/2: Replace Example-Based Testing with Property-Based Testing.
 * <br/>
 * Especially take some time to ponder how much of the tested code (eq. the solution) you should accept duplicating in the test.
 */
class ClaimTest {

    @Property
    void should_resolve_overdistance_if_limit_is_undefined(@ForAll Contract contract, @ForAll IncidentType incidentType,
                                                           @ForAll Location incidentLocation, @ForAll Garage destination) {
        Assume.that(!contract.limits().containsKey(incidentType));
        Assume.that(distanceBetween(incidentLocation, destination).isGreaterThan(Distance.ZERO));

        final var claim = Claim.builder()
                .incident(new Incident(incidentType, incidentLocation))
                .contract(contract)
                .build();

        final var actual = claim.resolveOverdistance(destination);

        assertThat(actual).contains(distanceBetween(incidentLocation, destination));
    }

    @Property
    void should_resolve_overdistance_if_garage_is_further_than_limit(@ForAll Distance limit, @ForAll IncidentType incidentType,
                                                                     @ForAll Location incidentLocation, @ForAll Garage destination) {
        Assume.that(distanceBetween(incidentLocation, destination).isGreaterThan(limit));

        final var claim = Claim.builder()
                .incident(new Incident(incidentType, incidentLocation))
                .contract(Contract.builder()
                        .limit(incidentType, limit)
                        .build())
                .build();

        final var actual = claim.resolveOverdistance(destination);

        assertThat(actual).contains(distanceBetween(incidentLocation, destination).minus(limit));
    }

    @Property
    void should_resolve_no_overdistance_if_garage_is_closer_than_limit(@ForAll Distance limit, @ForAll IncidentType incidentType,
                                                                       @ForAll Location incidentLocation, @ForAll Garage destination) {
        Assume.that(limit.isGreaterThan(distanceBetween(incidentLocation, destination)));

        final var claim = Claim.builder()
                .incident(new Incident(incidentType, incidentLocation))
                .contract(Contract.builder()
                        .limit(incidentType, limit)
                        .build())
                .build();

        final var actual = claim.resolveOverdistance(destination);

        assertThat(actual).isEmpty();
    }

    private static Distance distanceBetween(final Location origin, final Garage destination) {
        return origin.coordinates().distanceTo(destination.coordinates());
    }
}
