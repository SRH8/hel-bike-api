package com.sergiofraga.helsinkibikeapi.journey;

import com.sergiofraga.helsinkibikeapi.journey.model.JourneyDto;
import com.sergiofraga.helsinkibikeapi.journey.model.JourneyResponse;
import com.sergiofraga.helsinkibikeapi.journey.service.JourneyService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = JourneyController.class)
class JourneyControllerTest {

    @MockBean
    JourneyService journeyService;
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName(value = "When making a GET request to /api/v1/journeys it should return a page listing journeys")
    void whenGetRequestToJourneysEndpoint_thenShouldReturnJourneysList() throws Exception {
        JourneyDto journeyDto1 = new JourneyDto(12,LocalDateTime.parse("2021-05-31T23:57:25"), LocalDateTime.parse("2021-06-01T00:05:46"),
                94, "Laajalahden aukio", 122, "Teljäntie", 2345, 500);

        JourneyDto journeyDto2 = new JourneyDto(12,LocalDateTime.parse("2021-05-31T23:56:59"), LocalDateTime.parse("2021-06-01T00:07:14"),
                67, "Töölöntulli", 56, "Pasilan asema", 1946, 345);

        JourneyResponse response = new JourneyResponse(Arrays.asList(journeyDto1, journeyDto2), 0, 2, 2, 1, true);

        Mockito.when(journeyService.getJourneys(0, 2, "departureDate", "asc")).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/journeys?pageNo=0&pageSize=2&sortBy=departureDate&sortDir=asc"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(6)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageNo", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageSize", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.last", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.is(journeyDto1.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].departureDate", Matchers.is(journeyDto1.getDepartureDate().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].returnDate", Matchers.is(journeyDto1.getReturnDate().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id", Matchers.is(journeyDto2.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].departureDate", Matchers.is(journeyDto2.getDepartureDate().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].returnDate", Matchers.is(journeyDto2.getReturnDate().toString())));
    }

    @Test
    @DisplayName("When making a bad Get request to /api/v1/journeys it should return a Bad Request exception")
    void whenBadRequestToJourneysEndpoint_thenShouldReturnAnException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/journeys?pageNo=aaa"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertEquals("Failed to convert value of type 'java.lang.String' to required" +
                                " type 'int'; For input string: \"aaa\"",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}