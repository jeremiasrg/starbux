package com.jr.starbux.controller;

import com.jr.starbux.service.DrinkService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class DrinkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DrinkService service;

    @Test
    void shouldGetAllDrinks() throws Exception {
//        Mockito
//                .when(service.findAll())
//                .thenReturn()

        mockMvc.perform(get("/drinks"))
                .andDo(print())
                .andExpect(status().isOk());


    }

}
