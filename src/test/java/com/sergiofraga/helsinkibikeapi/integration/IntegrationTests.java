package com.sergiofraga.helsinkibikeapi.integration;

import com.sergiofraga.helsinkibikeapi.journey.JourneyRepository;
import com.sergiofraga.helsinkibikeapi.journey.model.Journey;
import com.sergiofraga.helsinkibikeapi.station.StationRepository;
import com.sergiofraga.helsinkibikeapi.station.model.Station;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class IntegrationTests {
    @Autowired
    StationRepository stationRepository;
    @Autowired
    JourneyRepository journeyRepository;
    @Autowired
    MockMvc mockMvc;

    @Container
    private static final MySQLContainer mysql = new MySQLContainer("mysql:latest")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @BeforeEach
    void setUp() {
        stationRepository.deleteAll();
        journeyRepository.deleteAll();
    }

    @BeforeAll
    static void setUpDatabaseProperties() {
        System.setProperty("spring.datasource.url", mysql.getJdbcUrl());
        System.setProperty("spring.datasource.username", mysql.getUsername());
        System.setProperty("spring.datasource.password", mysql.getPassword());
    }

    @Test
    @DisplayName("Given an empty database it should return 0 stations. After saving a station to the database it should return the added station")
    void whenMakingGetRequestToStationsEndpoint_shouldReturnTheStationsPresentInTheDatabase() throws Exception {
        Station station = new Station(3, 123, "Hanasaari", "Hanaholmen", "Hanasaari", "Hanasaarenranta 1", "Hanaholmsstranden 1",
                "Espoo", "Esbo", "CityBike Finland", 23, 24.840319, 60.16582);

        Assertions.assertThat(stationRepository.findAll()).isEmpty();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/stations"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(6)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageSize", Matchers.is(15)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements", Matchers.is(0)));

        stationRepository.save(station);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/stations"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(6)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageNo", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageSize", Matchers.is(15)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.last", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].fid", Matchers.is(station.getFid())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.is(station.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].nimi", Matchers.is(station.getNimi())));
    }

    @Test
    @DisplayName("Given an empty database it should return 0 journeys. After saving a journey to the database it should return the added journey")
    void whenMakingGetRequestToJourneysEndpoint_shouldReturnTheJourneysPresentInTheDatabase() throws Exception {
        Journey journey = new Journey(12, LocalDateTime.parse("2021-05-31T23:57:25"), LocalDateTime.parse("2021-06-01T00:05:46"),
                94, "Laajalahden aukio", 122, "Teljäntie", 2345, 500);

        Assertions.assertThat(stationRepository.findAll()).isEmpty();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/journeys"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(6)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageSize", Matchers.is(15)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements", Matchers.is(0)));

        journeyRepository.save(journey);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/journeys"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(6)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageNo", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageSize", Matchers.is(15)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.last", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].departureDate", Matchers.is(journey.getDepartureDate().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].returnDate", Matchers.is(journey.getReturnDate().toString())));
    }

    @Test
    @DisplayName("Given an empty database it should return a not found exception. After saving a station to the database it should return the added station")
    void whenMakingGetRequestToStationEndpoint_shouldReturnTheStationByItsId() throws Exception {
        Station station = new Station(1, 123, "Hanasaari", "Hanaholmen", "Hanasaari", "Hanasaarenranta 1", "Hanaholmsstranden 1",
                "Espoo", "Esbo", "CityBike Finland", 23, 24.840319, 60.16582);

        Station station2 = new Station(2, 63, "Keilalahti", "Kägelviken", "Keilalahti", "Keilalahdentie 2",
                "Kägelviksvägen 2", "Espoo", "Esbo", "CityBike Finland", 23, 28.840319,62.16582);

        Assertions.assertThat(stationRepository.findAll()).isEmpty();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/station?id=1"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"Station was not found for parameter id:{1}\"",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));

        stationRepository.save(station);
        stationRepository.save(station2);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/station?id=1"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(13)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fid", Matchers.is(station.getFid())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(station.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nimi", Matchers.is(station.getNimi())));
    }
}