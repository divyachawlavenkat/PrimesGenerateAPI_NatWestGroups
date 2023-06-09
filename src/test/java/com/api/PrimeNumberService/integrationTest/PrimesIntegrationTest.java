package com.api.PrimeNumberService.integrationTest;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;


/**
 * integration test cases
 * Testcases for all valid and invalid input number
 * Test cases for Objective 1 --> REST API service to calculate and returns all the prime numbers up to and including a valid input number provided.
 * Test Cases for Objective 2 --->REST API service will throw custom ResponseEntity message for invalid input number like 1,0, negative integers
 *
 * @author divyavenkatesh
 * @date 21/03/2023
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PrimesIntegrationTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    public void testGetPrimesForNumberTen() throws Exception {
        //given
        String requestUrl = "/primes/10";

        //when
        mockMvc.perform(MockMvcRequestBuilders.get(requestUrl))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Initial").value(10))
                .andExpect(jsonPath("$.Primes", hasSize(4)))
                .andExpect(jsonPath("$.Primes", containsInAnyOrder(2, 3, 5, 7)));
    }

    @Test
    public void testGetPrimesForNumberOne() throws Exception {
        //given
        String requestUrl = "/primes/1";

        //when
        mockMvc.perform(MockMvcRequestBuilders.get(requestUrl))
                //then
                .andExpect(jsonPath("$.Error").value("Invalid input number"))
                .andExpect(jsonPath("$.Message").value("To get valid Primes, Initial input number should not be less than 2 and should not be negative integer"));
    }

    @Test
    public void testGetPrimesForNumberZero() throws Exception {
        //given
        String requestUrl = "/primes/0";

        //when
        mockMvc.perform(MockMvcRequestBuilders.get(requestUrl))
                //then
                .andExpect(jsonPath("$.Error").value("Invalid input number"))
                .andExpect(jsonPath("$.Message").value("To get valid Primes, Initial input number should not be less than 2 and should not be negative integer"));
    }

    @Test
    public void testGetPrimesForNumberNegativeNumber() throws Exception {
        //given
        String requestUrl = "/primes/-45";

        //when
        mockMvc.perform(MockMvcRequestBuilders.get(requestUrl))
                //then
                .andExpect(jsonPath("$.Error").value("Invalid input number"))
                .andExpect(jsonPath("$.Message").value("To get valid Primes, Initial input number should not be less than 2 and should not be negative integer"));
    }

    @Test
    public void testGetPrimesForNumberTwenty() throws Exception {
        //given
        String requestUrl = "/primes/20";

        //when
        mockMvc.perform(MockMvcRequestBuilders.get(requestUrl))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Initial").value(20))
                .andExpect(jsonPath("$.Primes", hasSize(8)))
                .andExpect(jsonPath("$.Primes", containsInAnyOrder(2, 3, 5, 7, 11, 13, 17, 19)));
    }

    @Test
    public void testGetPrimesForNumberHundred() throws Exception {
        //given
        String requestUrl = "/primes/100";

        //when
        mockMvc.perform(MockMvcRequestBuilders.get(requestUrl))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Initial").value(100))
                .andExpect(jsonPath("$.Primes", hasSize(25)))
                .andExpect(jsonPath("$.Primes", containsInAnyOrder(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)));
    }


    @Test
    public void testGetPrimesForStringInvalidInputString() throws Exception {
        //given
        String requestUrl = "/primes/invalid";

        //when
        mockMvc.perform(MockMvcRequestBuilders.get(requestUrl))
                //then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetPrimesForStringInvalidInput() throws Exception {
        //given
        String requestUrl = "/primes/&&";

        //when
        mockMvc.perform(MockMvcRequestBuilders.get(requestUrl))
                //then
                .andExpect(status().isBadRequest());

    }

}
