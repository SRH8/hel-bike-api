package com.sergiofraga.helsinkibikeapi.station.model;

import lombok.Data;

/**
 * Station Data Transfer Object
 */
@Data
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