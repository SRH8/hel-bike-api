package com.sergiofraga.helsinkibikeapi.station.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Station Data Transfer Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationDto {

    private int fid;
    private int id;
    private String nimi;
    private String namn;
    private String name;
    private String osoite;
    private String adress;
    private String kaupunki;
    private String stad;
    private String operaattor;
    private int kapasiteet;
    private double x;
    private double y;
}