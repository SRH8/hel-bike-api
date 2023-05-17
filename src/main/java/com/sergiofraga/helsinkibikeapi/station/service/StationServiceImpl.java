package com.sergiofraga.helsinkibikeapi.station.service;

import com.sergiofraga.helsinkibikeapi.station.StationRepository;
import com.sergiofraga.helsinkibikeapi.station.model.Station;
import com.sergiofraga.helsinkibikeapi.station.model.StationDto;
import com.sergiofraga.helsinkibikeapi.station.model.StationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public StationResponse getStations(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Station> stations = stationRepository.findAll(pageable);

        List<Station> listOfStations = stations.getContent();

        List<StationDto> content = listOfStations.stream()
                .map(StationDto::mapToDto)
                .toList();

        StationResponse stationResponse = new StationResponse();
        stationResponse.setContent(content);
        stationResponse.setPageNo(stations.getNumber());
        stationResponse.setPageSize(stations.getSize());
        stationResponse.setTotalElements(stations.getTotalElements());
        stationResponse.setTotalPages(stations.getTotalPages());
        stationResponse.setLast(stations.isLast());

        return stationResponse;
    }

    @Override
    public StationDto getStationById(int id) {
        Optional<Station> optionalStation = stationRepository.findById(id);

        return optionalStation.map(StationDto::mapToDto).orElse(null);
    }
}