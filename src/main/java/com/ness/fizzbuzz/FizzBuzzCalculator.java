package com.ness.fizzbuzz;

import com.ness.fizzbuzz.util.FizzBuzzProcessor;

import java.util.List;

public class FizzBuzzCalculator {

    public static void main(String args[]){
        FizzBuzzProcessor fizzBuzzProcessor = new FizzBuzzProcessor();

        //first get a list of integers
        List<Integer>  numbers = fizzBuzzProcessor.getNumbers("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20");
        //second generate fizz buzz
        System.out.println(fizzBuzzProcessor.calculateFizzBuzz(numbers));

        System.out.println(fizzBuzzProcessor.getNumbersOfRange(1,20));

        //generate alfresco
        System.out.println(fizzBuzzProcessor.calculateFizzAlfresco(numbers));
        System.out.println(fizzBuzzProcessor.getNumbersOfRangeAlfresco(1,20));

    }


}
