package com.ness.fizzbuzz.service;

import java.util.List;
import java.util.Map;

public interface NumbersService {

    Map<String, String> calculateFizzAlfresco(List<Integer> numbers);

    Map<String, String> getAlfrescoWithReport(int startRange, int endRange);

}
