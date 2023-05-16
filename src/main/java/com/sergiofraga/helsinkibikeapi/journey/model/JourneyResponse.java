package com.sergiofraga.helsinkibikeapi.journey.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Model for the GET journey response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JourneyResponse {

    private List<JourneyDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}