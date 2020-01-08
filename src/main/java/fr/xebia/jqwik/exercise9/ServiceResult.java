package fr.xebia.jqwik.exercise9;

import java.util.Objects;
import java.util.Optional;

abstract class ServiceResult {

    static ServiceResult serviceResponse(final String result) {
        return new CallSuccess(result);
    }

    static ServiceResult callFailed(final String reason) {
        return new CallFailure(reason);
    }

    abstract boolean isFailure();

    abstract Optional<String> getResult();

    static final class CallSuccess extends ServiceResult {

        private final String result;

        private CallSuccess(final String result) {
            this.result = result;
        }

        @Override
        boolean isFailure() {
            return false;
        }

        @Override
        Optional<String> getResult() {
            return Optional.of(result);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CallSuccess that = (CallSuccess) o;
            return Objects.equals(result, that.result);
        }

        @Override
        public int hashCode() {
            return Objects.hash(result);
        }

        @Override
        public String toString() {
            return "CallSuccess{" +
                    "result='" + result + '\'' +
                    '}';
        }
    }

    static final class CallFailure extends ServiceResult {

        private final String reason;

        private CallFailure(final String reason) {
            this.reason = reason;
        }

        @Override
        boolean isFailure() {
            return true;
        }

        @Override
        Optional<String> getResult() {
            return Optional.empty();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CallFailure that = (CallFailure) o;
            return Objects.equals(reason, that.reason);
        }

        @Override
        public int hashCode() {
            return Objects.hash(reason);
        }

        @Override
        public String toString() {
            return "CallFailure{" +
                    "reason='" + reason + '\'' +
                    '}';
        }
    }

}
