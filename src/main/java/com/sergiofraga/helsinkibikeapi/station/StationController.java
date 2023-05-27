package com.sergiofraga.helsinkibikeapi.station;

import com.sergiofraga.helsinkibikeapi.station.model.StationDto;
import com.sergiofraga.helsinkibikeapi.station.model.StationResponse;
import com.sergiofraga.helsinkibikeapi.station.service.StationService;
import com.sergiofraga.helsinkibikeapi.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class StationController {

    private final StationService stationService;

    StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @CrossOrigin
    @GetMapping(value = "/api/v1/stations")
    public StationResponse getStations(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_STATIONS_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return stationService.getStations(pageNo, pageSize, sortBy, sortDir);
    }

    @CrossOrigin
    @GetMapping(value = "/api/v1/station")
    public StationDto getStationById(@RequestParam(value = "id") int id) {
        StationDto station = stationService.getStationById(id);

        if(station == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Station was not found for parameter id:{" + id + "}");
        }

        return station;
    }
}