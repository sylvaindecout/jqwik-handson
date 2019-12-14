package fr.xebia.jqwik.exercise6;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
final class Incident {

    String id;
    Instant date;
    Country country;
    Type type;
    Vehicle vehicle;

    enum Type {
        ACCIDENT, BREAKDOWN
    }

}
