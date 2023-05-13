package com.sergiofraga.helsinkibikeapi.station.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Model for the GET station response
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationResponse {

    private List<StationDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}