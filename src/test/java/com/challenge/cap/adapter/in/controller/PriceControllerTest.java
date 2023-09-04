package com.challenge.cap.adapter.in.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetPriceAt10AMOnDay14() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        performTest(applicationDate, 35455L, 1L)
                .andExpect(jsonPath("$.price").value(Double.valueOf("35.50")));
    }

    @Test
    void testGetPriceAt4PMOnDay14() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0);
        performTest(applicationDate, 35455L, 1L)
                .andExpect(jsonPath("$.price").value(Double.valueOf("25.45")));
    }

    @Test
    void testGetPriceAt9PMOnDay14() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0);
        performTest(applicationDate, 35455L, 1L)
                .andExpect(jsonPath("$.price").value(Double.valueOf("35.50")));
    }

    @Test
    void testGetPriceAt10AMOnDay15() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0);
        performTest(applicationDate, 35455L, 1L)
                .andExpect(jsonPath("$.price").value(Double.valueOf("30.50")));
    }

    @Test
    void testGetPriceAt9PMOnDay16() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0);
        performTest(applicationDate, 35455L, 1L)
                .andExpect(jsonPath("$.price").value(Double.valueOf("38.95")));
    }

    private ResultActions performTest(LocalDateTime applicationDate, Long productId, Long brandId) throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/prices")
                .param("application_date", applicationDate.toString())
                .param("product_id", productId.toString())
                .param("brand_id", brandId.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        return result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.price").isNumber())
                .andExpect(jsonPath("$.startDate").isNotEmpty())
                .andExpect(jsonPath("$.endDate").isNotEmpty())
                .andExpect(jsonPath("$.currency").isString());
    }
}
