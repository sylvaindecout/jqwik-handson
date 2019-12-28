package fr.xebia.jqwik.exercise9;

import lombok.*;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

public abstract class ServiceResult {

    public static ServiceResult serviceResponse(final String result){
        return new CallSuccess(result);
    }

    public static ServiceResult callFailed(final String reason){
        return new CallFailure(reason);
    }

    public abstract boolean isFailure();
    public abstract Optional<String> getResult();

    @ToString
    @EqualsAndHashCode(callSuper = false)
    @AllArgsConstructor(access = PRIVATE)
    public static final class CallSuccess extends ServiceResult {
        String result;

        @Override
        public boolean isFailure() {
            return false;
        }

        @Override
        public Optional<String> getResult() {
            return Optional.of(result);
        }
    }

    @ToString
    @EqualsAndHashCode(callSuper = false)
    @AllArgsConstructor(access = PRIVATE)
    public static final class CallFailure extends ServiceResult {
        String reason;

        @Override
        public boolean isFailure() {
            return true;
        }

        @Override
        public Optional<String> getResult() {
            return Optional.empty();
        }
    }

}
