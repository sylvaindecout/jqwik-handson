package fr.xebia.jqwik.exercise9;


import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceResultTest {

    @Property
    void should_consider_results_with_same_value_as_equal(@ForAll String value){
        assertThat(ServiceResult.serviceResponse(value)).isEqualTo(ServiceResult.serviceResponse(value));
    }

    @Property
    void should_not_consider_results_with_different_values_as_equal(@ForAll String value1, @ForAll String value2){
        Assume.that(!value1.equals(value2));
        assertThat(ServiceResult.serviceResponse(value1)).isNotEqualTo(ServiceResult.serviceResponse(value2));
    }

    @Property
    void should_consider_call_failures_with_same_reason_as_equal(@ForAll String reason){
        assertThat(ServiceResult.callFailed(reason)).isEqualTo(ServiceResult.callFailed(reason));
    }

    @Property
    void should_not_consider_call_failures_with_different_reason_as_equal(@ForAll String reason1, @ForAll String reason2){
        Assume.that(!reason1.equals(reason2));
        assertThat(ServiceResult.callFailed(reason1)).isNotEqualTo(ServiceResult.callFailed(reason2));
    }

    @Property
    void should_not_consider_result_as_equal_to_call_failure(@ForAll String value, @ForAll String reason){
        assertThat(ServiceResult.serviceResponse(value)).isNotEqualTo(ServiceResult.callFailed(reason));
    }

    @Property
    void should_consider_call_failure_as_failure(@ForAll String reason){
        assertThat(ServiceResult.callFailed(reason).isFailure()).isTrue();
    }

    @Property
    void should_not_consider_call_valid_response_as_failure(@ForAll String value){
        assertThat(ServiceResult.serviceResponse(value).isFailure()).isFalse();
    }

    @Property
    void should_not_expose_result_if_call_failed(@ForAll String reason){
        assertThat(ServiceResult.callFailed(reason).getResult()).isEmpty();
    }

    @Property
    void should_expose_result_if_call_succeeded(@ForAll String value){
        assertThat(ServiceResult.serviceResponse(value).getResult()).contains(value);
    }

}
