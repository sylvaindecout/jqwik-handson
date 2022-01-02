package fr.xebia.jqwik.exercise6;

import static com.google.common.base.Preconditions.checkArgument;

record VehicleType(int code) {

    VehicleType {
        checkArgument(code > -1,
                "Code is expected to be a positive value - value: %s", code);
        checkArgument(code < 20,
                "Code is expected to be a value lower than 20 - value: %s", code);
    }

}
