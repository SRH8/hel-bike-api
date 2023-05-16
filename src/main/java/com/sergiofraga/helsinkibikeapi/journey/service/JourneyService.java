package com.sergiofraga.helsinkibikeapi.journey.service;

import com.sergiofraga.helsinkibikeapi.journey.model.JourneyResponse;

/**
 * Interface for Journey CRUD operations
 */
public interface JourneyService {

    /**
     * Creates a paginated list of journeys
     *
     * @param pageNo page number
     * @param pageSize number of elements to be displayed in a page
     * @param sortBy the property that will be used for sorting
     * @param sortDir sort type
     * @return JourneyResponse
     */
    JourneyResponse getJourneys(int pageNo, int pageSize, String sortBy, String sortDir);
}