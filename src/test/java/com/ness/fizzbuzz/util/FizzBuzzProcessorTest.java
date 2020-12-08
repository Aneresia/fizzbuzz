package com.ness.fizzbuzz.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class FizzBuzzProcessorTest {
  public static final String RANDOM_NUMBERS = "127 12";
  public static final String RANDOM_NUMBERS_WITH_WORDS = "123 abc 12";
  public static final String FIZZ_BUZZ =
      "1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz 16 17 fizz 19 buzz";
  public static final String NUMBERS_FIZZ = "127 fizz";


  @InjectMocks FizzBuzzProcessor fizzBuzzProcessor;

    @Test
    @DisplayName("gets correct List of Integers")
    public void createListOfIntegers() {
        // given
        List<Integer> simpleListNumbers = fizzBuzzProcessor.getNumbers(RANDOM_NUMBERS);

        // then
        assertEquals(127 , simpleListNumbers.get(0));
    }

    @Test
    @DisplayName("gets exception because string does not contain only numbers")
    public void createListOFNumbersException() {
        // then
        assertThrows(NumberFormatException.class, () -> fizzBuzzProcessor.getNumbers(RANDOM_NUMBERS_WITH_WORDS));
    }

    @Test
  @DisplayName("a String is returned back")
  public void calculateFizzBuzzShowsNumbers() {
    // given
    List<Integer> simpleListNumbers = fizzBuzzProcessor.getNumbers(RANDOM_NUMBERS);

    // then
    assertEquals(NUMBERS_FIZZ, fizzBuzzProcessor.calculateFizzBuzz(simpleListNumbers));
  }

    @Test
    @DisplayName("a String with fizz buzz is returned")
    public void calculateFizzBuzzRangeShowsNumbers() {
        // then
        assertEquals(FIZZ_BUZZ, fizzBuzzProcessor.getNumbersOfRange(1, 20));
    }
}
