package com.github.dhiegorp.beerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dhiegorp.beerservice.web.model.BeerDto;
import com.github.dhiegorp.beerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(
                get("/api/v1/beer/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beer = getValid();
        String jsonBeer = objectMapper.writeValueAsString(beer);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBeer))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto beerDto = getValid();
        String jsonBeer = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBeer))
                .andExpect(status().isNoContent());
    }

    public BeerDto getValid() {
        return BeerDto.builder()
                .beerName("My Beerloved")
                .beerStyle(BeerStyleEnum.IPA)
                .price(new BigDecimal("7.99"))
                .upc(123123000L)
                .build();
    }
}