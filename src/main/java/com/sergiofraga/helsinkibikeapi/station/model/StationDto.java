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

    /**
     * Maps a Station entity to a Station Data Transfer object
     *
     * @param station object to be mapped
     * @return StationDto mapped station
     */
    public static StationDto mapToDto(Station station){
        StationDto stationDto = new StationDto();
        stationDto.setFid(station.getFid());
        stationDto.setId(station.getId());
        stationDto.setNimi(station.getNimi());
        stationDto.setNamn(station.getNamn());
        stationDto.setName(station.getName());
        stationDto.setOsoite(station.getOsoite());
        stationDto.setAdress(station.getAdress());
        stationDto.setKaupunki(station.getKaupunki());
        stationDto.setStad(station.getStad());
        stationDto.setOperaattor(station.getOperaattor());
        stationDto.setKapasiteet(station.getKapasiteet());
        stationDto.setX(station.getX());
        stationDto.setY(station.getY());

        return stationDto;
    }
}