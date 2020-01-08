package fr.xebia.jqwik.exercise6;

import java.util.Collection;
import java.util.Objects;

final class Contract {

    private final PolicyNumber policyNumber;
    private final ValiditySpan validitySpan;
    private final Collection<Country> coveredCountries;
    private final Collection<VehicleType> coveredVehicleTypes;

    private Contract(final PolicyNumber policyNumber, final ValiditySpan validitySpan,
                     final Collection<Country> coveredCountries, final Collection<VehicleType> coveredVehicleTypes) {
        this.policyNumber = policyNumber;
        this.validitySpan = validitySpan;
        this.coveredCountries = coveredCountries;
        this.coveredVehicleTypes = coveredVehicleTypes;
    }

    boolean covers(final Incident incident) {
        return this.validitySpan.contains(incident.getDate())
                && this.coveredCountries.contains(incident.getCountry())
                && this.coveredVehicleTypes.contains(incident.getVehicle().getType());
    }

    PolicyNumber getPolicyNumber() {
        return policyNumber;
    }

    ValiditySpan getValiditySpan() {
        return validitySpan;
    }

    Collection<Country> getCoveredCountries() {
        return coveredCountries;
    }

    Collection<VehicleType> getCoveredVehicleTypes() {
        return coveredVehicleTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return Objects.equals(policyNumber, contract.policyNumber) &&
                Objects.equals(validitySpan, contract.validitySpan) &&
                Objects.equals(coveredCountries, contract.coveredCountries) &&
                Objects.equals(coveredVehicleTypes, contract.coveredVehicleTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNumber, validitySpan, coveredCountries, coveredVehicleTypes);
    }

    @Override
    public String toString() {
        return "Contract{" +
                "policyNumber=" + policyNumber +
                ", validitySpan=" + validitySpan +
                ", coveredCountries=" + coveredCountries +
                ", coveredVehicleTypes=" + coveredVehicleTypes +
                '}';
    }

    static ContractBuilder builder() {
        return new ContractBuilder();
    }

    static final class ContractBuilder {
        private PolicyNumber policyNumber;
        private ValiditySpan validitySpan;
        private Collection<Country> coveredCountries;
        private Collection<VehicleType> coveredVehicleTypes;

        private ContractBuilder() {}

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
