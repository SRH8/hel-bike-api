package com.sergiofraga.helsinkibikeapi.journey.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Journey Data Transfer Object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JourneyDto {

    private int id;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private int departureStationId;
    private String departureStationName;
    private int returnStationId;
    private String returnStationName;
    private int distanceCoveredM;
    private int durationS;
}