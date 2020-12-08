package com.ness.fizzbuzz;

import com.ness.fizzbuzz.util.FizzBuzzProcessor;
import com.ness.fizzbuzz.util.OperationsEnum;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FizzBuzzCalculator {

    public static void main(String args[]){
        FizzBuzzProcessor fizzBuzzProcessor = new FizzBuzzProcessor();

        //first get a list of integers
        List<Integer>  numbers = fizzBuzzProcessor.getNumbers("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20");
        //second generate fizz buzz
        System.out.println(fizzBuzzProcessor.calculateFizzBuzz(numbers));

        System.out.println(fizzBuzzProcessor.getNumbersOfRange(1,20));
    }


}
