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

    /**
     * Maps a Journey entity to a Journey Data Transfer object
     *
     * @param journey object to be mapped
     * @return JourneyDto mapped journey
     */
    public static JourneyDto mapToDto(Journey journey) {
        JourneyDto journeyDto = new JourneyDto();
        journeyDto.setId(journey.getId());
        journeyDto.setDepartureDate(journey.getDepartureDate());
        journeyDto.setReturnDate(journey.getReturnDate());
        journeyDto.setDepartureStationId(journey.getDepartureStationId());
        journeyDto.setDepartureStationName(journey.getDepartureStationName());
        journeyDto.setReturnStationId(journey.getReturnStationId());
        journeyDto.setReturnStationName(journey.getReturnStationName());
        journeyDto.setDistanceCoveredM(journey.getDistanceCoveredM());
        journeyDto.setDurationS(journey.getDurationS());

        return journeyDto;
    }
}