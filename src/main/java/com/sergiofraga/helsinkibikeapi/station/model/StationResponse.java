package com.sergiofraga.helsinkibikeapi.station.model;

import lombok.Data;

import java.util.List;

/**
 * Model for the GET station response
 */
@Data
public class StationResponse {

    private List<StationDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}