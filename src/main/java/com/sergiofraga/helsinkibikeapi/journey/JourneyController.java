package com.sergiofraga.helsinkibikeapi.journey;

import com.sergiofraga.helsinkibikeapi.journey.model.JourneyResponse;
import com.sergiofraga.helsinkibikeapi.journey.service.JourneyService;
import com.sergiofraga.helsinkibikeapi.util.AppConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JourneyController {

    private final JourneyService journeyService;

    JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @GetMapping(value = "/api/v1/journeys")
    public JourneyResponse getJourneys(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_JOURNEYS_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return  journeyService.getJourneys(pageNo, pageSize, sortBy, sortDir);
    }
}