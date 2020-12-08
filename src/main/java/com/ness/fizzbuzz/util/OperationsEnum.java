package com.ness.fizzbuzz.util;

import java.util.stream.Stream;

public enum OperationsEnum {

    FIFHTEEN(15 , "fizzbuzz"),
    THREE( 3, "fizz"),
    FIVE( 5, "buzz");

    private int number;
    private String text;

    OperationsEnum(int number, String text) {
        this.number = number;
        this.text = text;
    }

    public static String fizzBuzz(Integer number) {
        return Stream.of(FIFHTEEN, THREE, FIVE)
                .filter(op -> Integer.valueOf(number) % op.number == 0)
                .findFirst()
                .map(op -> op.text)
                .orElse(Integer.toString((number)));
    }
}
