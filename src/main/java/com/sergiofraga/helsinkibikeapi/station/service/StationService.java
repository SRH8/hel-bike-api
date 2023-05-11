package com.sergiofraga.helsinkibikeapi.station.service;

import com.sergiofraga.helsinkibikeapi.station.model.StationDto;
import com.sergiofraga.helsinkibikeapi.station.model.StationResponse;

/**
 * Interface for Station CRUD operations
 */
public interface StationService {

    /**
     * Creates a paginated list of stations
     *
     * @param pageNo page number
     * @param pageSize number of elements to be displayed in a page
     * @param sortBy the property that will be used for sorting
     * @param sortDir sort type
     * @return StationResponse
     */
    StationResponse getStations(int pageNo, int pageSize, String sortBy, String sortDir);

    /**
     * Fetches a station by its id
     *
     * @param id the id of the station
     * @return StationDto station object
     */
    StationDto getStationById(int id);
}