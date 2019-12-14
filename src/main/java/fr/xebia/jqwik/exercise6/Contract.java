package fr.xebia.jqwik.exercise6;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
final class Contract {

    PolicyNumber policyNumber;
    ValiditySpan validitySpan;
    Collection<Country> coveredCountries;
    Collection<VehicleType> coveredVehicleTypes;

    boolean covers(final Incident incident) {
        return this.validitySpan.contains(incident.getDate())
                && this.coveredCountries.contains(incident.getCountry())
                && this.coveredVehicleTypes.contains(incident.getVehicle().getType());
    }

}
