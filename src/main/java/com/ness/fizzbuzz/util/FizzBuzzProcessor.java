package com.ness.fizzbuzz.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FizzBuzzProcessor {

    public String calculateFizzBuzz(List<Integer> numbers) {
        return numbers.stream()
                .map(OperationsEnum::fizzBuzz)
                .collect(Collectors.joining( " "));
    }

    public String getNumbersOfRange(int startRange, int endRange) throws NumberFormatException{
        return Stream.iterate(startRange, n -> n + 1)
                .limit(endRange)
                .map(OperationsEnum::fizzBuzz)
                .collect(Collectors.joining(" "));
    }

    public List<Integer> getNumbers(String numbers) throws NumberFormatException{
        String[] x = numbers.trim().split("\\s+");
        return Stream.of(x).map(Integer::parseInt).collect(Collectors.toList());
    }
}
