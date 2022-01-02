package fr.xebia.jqwik.exercise6;

import java.util.Collection;

record Contract(PolicyNumber policyNumber, ValiditySpan validitySpan,
                Collection<Country> coveredCountries,
                Collection<VehicleType> coveredVehicleTypes) {

    boolean covers(final Incident incident) {
        return this.validitySpan.contains(incident.date())
                && this.coveredCountries.contains(incident.country())
                && this.coveredVehicleTypes.contains(incident.vehicle().type());
    }

    static ContractBuilder builder() {
        return new ContractBuilder();
    }

    static final class ContractBuilder {
        private PolicyNumber policyNumber;
        private ValiditySpan validitySpan;
        private Collection<Country> coveredCountries;
        private Collection<VehicleType> coveredVehicleTypes;

        private ContractBuilder() {
        }

        ContractBuilder policyNumber(PolicyNumber policyNumber) {
            this.policyNumber = policyNumber;
            return this;
        }

        ContractBuilder validitySpan(ValiditySpan validitySpan) {
            this.validitySpan = validitySpan;
            return this;
        }

        ContractBuilder coveredCountries(Collection<Country> coveredCountries) {
            this.coveredCountries = coveredCountries;
            return this;
        }

        ContractBuilder coveredVehicleTypes(Collection<VehicleType> coveredVehicleTypes) {
            this.coveredVehicleTypes = coveredVehicleTypes;
            return this;
        }

        Contract build() {
            return new Contract(policyNumber, validitySpan, coveredCountries, coveredVehicleTypes);
        }
    }
}
