package com.sergiofraga.helsinkibikeapi.journey.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "journeys")
public class Journey {

    private @Id @GeneratedValue int id;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private int departureStationId;
    private String departureStationName;
    private int returnStationId;
    private String returnStationName;
    private int distanceCoveredM;
    private int durationS;
}