package com.sergiofraga.helsinkibikeapi.station.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "stations")
public class Station {

    private @Id @GeneratedValue int fid;
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