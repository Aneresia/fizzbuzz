package com.ness.fizzbuzz.controller;

import com.ness.fizzbuzz.service.NumbersService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EnableAutoConfiguration
@Controller
@RequestMapping("/fizzbuzz")
public class FizzBuzzController {

  private final NumbersService numbersService;

  public FizzBuzzController(NumbersService numbersService) {
    this.numbersService = numbersService;
  }

  @ApiOperation(value = "Returns the fizz buzz conversion of given numbers")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Fizz Buzz was generated"),
          @ApiResponse(code = 400, message = "The string was not correct")})
  @GetMapping
  public Map<String, String> calculateFizzBuzzNumbers(@RequestParam(value = "numbers") String numbers) {

    return numbersService.calculateFizzAlfresco(getNumbers(numbers));
  }

  @ApiOperation(value = "Returns the fizz buzz output for a given range of numbers")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Fizz Buzz was generated"),
          @ApiResponse(code = 400, message = "The range was not a number")})
  @GetMapping("/range/{startRange}/{endRange}")
  public ModelAndView calculateFizzBuzzForRange(@PathVariable(name = "startRange") Integer startRange,
                                                @PathVariable("endRange") Integer endRange) {
    Map<String, String> messages = numbersService.getAlfrescoWithReport(startRange, endRange);
    ModelAndView mav = new ModelAndView("home::messages");
    mav.addObject("messages",messages);
    return mav;
  }

  private List<Integer> getNumbers(String numbers) throws NumberFormatException{
    String[] x = numbers.trim().split("\\s+");
    return Stream.of(x).map(Integer::parseInt).collect(Collectors.toList());
  }

}
