package com.sergiofraga.helsinkibikeapi.station;

import com.sergiofraga.helsinkibikeapi.station.model.StationDto;
import com.sergiofraga.helsinkibikeapi.station.model.StationResponse;
import com.sergiofraga.helsinkibikeapi.station.service.StationService;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(controllers = StationController.class)
class StationControllerTest {

    @MockBean
    private StationService stationService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("When making a GET request to /api/v1/stations it should return a page listing stations")
    void whenGetRequestToStationsEndpoint_thenShouldReturnStationList() throws Exception {
        StationDto station1 = new StationDto(133, 43, "Hanasaari", "Hanaholmen", "Hanasaari", "Hanasaarenranta 1",
                "Hanaholmsstranden 1", "Espoo", "Esbo", "CityBike Finland", 23, 24.840319,60.16582);

        StationDto station2 = new StationDto(123, 63, "Keilalahti", "Kägelviken", "Keilalahti", "Keilalahdentie 2",
                "Kägelviksvägen 2", "Espoo", "Esbo", "CityBike Finland", 23, 28.840319,62.16582);

        StationResponse response = new StationResponse(Arrays.asList(station1, station2), 0, 2, 2, 1,true);

        Mockito.when(stationService.getStations(0, 2, "nimi", "asc")).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/stations?pageNo=0&pageSize=2&sortBy=nimi&sortDir=asc"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(6)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageNo", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageSize", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.last", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].fid", Matchers.is(station1.getFid())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.is(station1.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].nimi", Matchers.is(station1.getNimi())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].fid", Matchers.is(station2.getFid())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id", Matchers.is(station2.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].nimi", Matchers.is(station2.getNimi())));
    }

    @Test
    @DisplayName("When making a GET request to /api/v1/station it should return the station with the given id")
    void whenGetRequestToStationByIdEndpoint_thenShouldReturnTheStation() throws Exception {
        StationDto station = new StationDto(133, 43, "Hanasaari", "Hanaholmen", "Hanasaari", "Hanasaarenranta 1",
                "Hanaholmsstranden 1", "Espoo", "Esbo", "CityBike Finland", 23, 24.840319,60.16582);

        Mockito.when(stationService.getStationById(station.getFid())).thenReturn(station);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/station?id=133"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(13)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fid", Matchers.is(station.getFid())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(station.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nimi", Matchers.is(station.getNimi())));
    }

    @Test
    @DisplayName("When making a Get request to /api/v1/station with a non-existent id it should return a Not Found exception")
    void whenGetRequestToStationByIdEndPointWithNonExistentId_thenShouldReturnAnException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/station?id=1000"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"Station was not found for parameter id:{1000}\"",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}