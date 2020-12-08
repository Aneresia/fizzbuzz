package com.ness.fizzbuzz.service.impl;

import com.ness.fizzbuzz.service.NumbersService;
import com.ness.fizzbuzz.util.OperationsEnum;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NumbersServiceImpl implements NumbersService {

    private static final String STR_INTEGER = "integer";

    public String calculateFizzBuzz(List<Integer> numbers) {
        return numbers.stream()
                .map(OperationsEnum::fizzBuzz)
                .collect(Collectors.joining( " "));
    }

    public Map<String, String> calculateFizzAlfresco(List<Integer> numbers) {
        String text =  numbers.stream()
                .map(OperationsEnum::alfresco)
                .collect(Collectors.joining( " "));
        return createReport(text);
    }

    public String getNumbersOfRange(int startRange, int endRange) throws NumberFormatException{
        return Stream.iterate(startRange, n -> n + 1)
                .limit(endRange)
                .map(OperationsEnum::fizzBuzz)
                .collect(Collectors.joining(" "));
    }

    public String getNumbersOfRangeAlfresco(int startRange, int endRange) throws NumberFormatException{
        return Stream.iterate(startRange, n -> n + 1)
                .limit(endRange)
                .map(OperationsEnum::alfresco)
                .collect(Collectors.joining(" "));
    }

    public Map<String, String> getAlfrescoWithReport(int startRange, int endRange) throws NumberFormatException{
        return createReport(getNumbersOfRangeAlfresco(startRange, endRange));
    }

    private Map<String, String> createReport(String text) {
        Map<String, Integer> report = new HashMap<>();
        report.put(STR_INTEGER, 0);

        Stream.of(text.split(" ")).forEach(word -> {
            if (isNumeric(word)) {
                report.put(STR_INTEGER, report.get(STR_INTEGER) + 1);
            } else {
                int count = report.getOrDefault(word, 0);
                report.put(word, count + 1);
            }
        });

        Map<String, String> reportString = new LinkedHashMap<>();
        report.forEach((key, value) -> reportString.put(key, String.valueOf(value)));
        reportString.put("TEXT", text);
        return reportString;
    }

    public List<Integer> getNumbers(String numbers) throws NumberFormatException{
        String[] x = numbers.trim().split("\\s+");
        return Stream.of(x).map(Integer::parseInt).collect(Collectors.toList());
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
