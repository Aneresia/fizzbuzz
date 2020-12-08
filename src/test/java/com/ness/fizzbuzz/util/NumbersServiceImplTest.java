package com.ness.fizzbuzz.util;

import com.ness.fizzbuzz.service.impl.NumbersServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class NumbersServiceImplTest {
    private static final String RANDOM_NUMBERS = "127 12";
    private static final String RANDOM_NUMBERS_WITH_WORDS = "123 abc 12";
    private static final String FIZZ_BUZZ =
            "1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz 16 17 fizz 19 buzz";
    private static final String ALFRESCO_FIZZ_BUZZ =
            "1 2 alfresco 4 buzz fizz 7 8 fizz buzz 11 fizz alfresco 14 fizzbuzz 16 17 fizz 19 buzz";
    private static final String NUMBERS_FIZZ = "127 fizz";


    @InjectMocks
    NumbersServiceImpl numbersServiceImpl;

    @Test
    @DisplayName("gets correct List of Integers")
    void createListOfIntegers() {
        // given
        List<Integer> simpleListNumbers = numbersServiceImpl.getNumbers(RANDOM_NUMBERS);

        // then
        assertEquals(127, simpleListNumbers.get(0));
    }

    @Test
    @DisplayName("gets exception because string does not contain only numbers")
    void createListOFNumbersException() {
        // then
        assertThrows(NumberFormatException.class, () -> numbersServiceImpl.getNumbers(RANDOM_NUMBERS_WITH_WORDS));
    }

    @Test
    @DisplayName("a String is returned back")
    void calculateFizzBuzzShowsNumbers() {
        // given
        List<Integer> simpleListNumbers = numbersServiceImpl.getNumbers(RANDOM_NUMBERS);

        // then
        assertEquals(NUMBERS_FIZZ, numbersServiceImpl.calculateFizzBuzz(simpleListNumbers));
    }

    @Test
    @DisplayName("a String with fizz buzz is returned")
    void calculateFizzBuzzRangeShowsNumbers() {
        // then
        assertEquals(FIZZ_BUZZ, numbersServiceImpl.getNumbersOfRange(1, 20));
    }

    @Test
    @DisplayName("a String with fizz buzz alfresco is returned from range")
    void calculateAlfrescoRangeShowsNumbers() {
        // then
        assertEquals(ALFRESCO_FIZZ_BUZZ, numbersServiceImpl.getNumbersOfRangeAlfresco(1, 20));
    }

    @Test
    @DisplayName("a String with fizz buzz alfresco is returned")
    void calculateAlfrescoRandomNumbers() {
        //given
        List<Integer> simpleListNumbers = numbersServiceImpl.getNumbers("127 3 6 23 30 15 5");

        // then
//        assertEquals("127 alfresco fizz alfresco alfresco fizzbuzz buzz", numbersServiceImpl.calculateFizzAlfresco(simpleListNumbers));
    }
}
