package com.ness.fizzbuzz.controller;

import com.ness.fizzbuzz.service.NumbersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class FizzBuzzControllerIT {

    private static final String ALFRESCO= "1 2 alfresco 4 buzz fizz 7 8 fizz buzz 11 fizz alfresco 14 fizzbuzz 16 17 fizz 19 buzz";
    Map<String, String> report;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private NumbersService numbersService;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        report = new HashMap<>();
        report.put("integer", "10");
        report.put("fizz", "4");
        report.put("buzz", "3");
        report.put("fizzbuzz", "1");
        report.put("alfresco", "2");
        report.put("TEXT", ALFRESCO);
    }

    @Test
    void testRangeOfNumbers() throws Exception {
        doReturn(report).when(numbersService).getAlfrescoWithReport(1, 20);
        mockMvc
                .perform(get("/fizzbuzz/range"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testRangeOfNumbersWithParams() throws Exception {
        when(numbersService.getAlfrescoWithReport(1, 20))
                .thenReturn(report);
        mockMvc
                .perform(get("/fizzbuzz/range").param("startRange", "1").param("endRange","20"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.alfresco", is("2")));
    }

    @Test
    void testRangeOfNumbersWithText() throws Exception {
        doThrow(new NumberFormatException()).when(numbersService).getAlfrescoWithReport(anyInt(), anyInt());
        mockMvc
                .perform(get("/fizzbuzz/range"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testOnlyNumbersForGivenString() throws Exception {
        doThrow(new NumberFormatException()).when(numbersService).calculateFizzAlfresco(any());
        mockMvc
                .perform(get("/fizzbuzz"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testOnlyNumbersForGivenNumbersWithText() throws Exception {

        doThrow(new NumberFormatException()).when(numbersService).calculateFizzAlfresco(any());
        mockMvc
                .perform(get("/fizzbuzz").param("numbers", "1 2 3 4 abc 6"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testOnlyNumbersForGivenNumbersOnly() throws Exception {
        List<Integer> texts = new ArrayList<>();
        texts.add(1);
        texts.add(2);
        texts.add(3);
        texts.add(4);
        texts.add(5);
        texts.add(6);

        when(numbersService.calculateFizzAlfresco(texts)).thenReturn(any());
        mockMvc
                .perform(get("/fizzbuzz").param("numbers", "1 2 3 4 5 6"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}