package com.ness.fizzbuzz.util;

import java.util.stream.Stream;

public enum OperationsEnum {

    FIFHTEEN(15 , "fizzbuzz"),
    THREE( 3, "fizz"),
    FIVE( 5, "buzz"),
    ALFRESCO(3, "alfresco");

    private int number;
    private String text;

    OperationsEnum(int number, String text) {
        this.number = number;
        this.text = text;
    }

    public static String fizzBuzz(Integer number) {
        return Stream.of(FIFHTEEN, THREE, FIVE)
                .filter(op -> number % op.number == 0)
                .findFirst()
                .map(op -> op.text)
                .orElse(Integer.toString((number)));
    }

    public static String alfresco(Integer number) {
        return Stream.of(ALFRESCO, FIFHTEEN, THREE, FIVE)
                .filter(op -> (op == ALFRESCO && Integer.toString(number).contains(String.valueOf(op.number)))
                        || (op != ALFRESCO && number % op.number == 0))
                .findFirst().map(op -> op.text).orElse(Integer.toString(number));
    }
}
