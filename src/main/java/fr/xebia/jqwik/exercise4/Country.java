package fr.xebia.jqwik.exercise4;

import static com.google.common.base.Preconditions.checkArgument;

record Country(String code) {

    Country {
        checkArgument(code.length() == 2,
                "Length of country code ('%s') is invalid", code);
    }

}
