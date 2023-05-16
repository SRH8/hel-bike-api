package com.sergiofraga.helsinkibikeapi.journey.service;

import com.sergiofraga.helsinkibikeapi.journey.JourneyRepository;
import com.sergiofraga.helsinkibikeapi.journey.model.Journey;
import com.sergiofraga.helsinkibikeapi.journey.model.JourneyDto;
import com.sergiofraga.helsinkibikeapi.journey.model.JourneyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourneyServiceImpl implements JourneyService {

    private final JourneyRepository journeyRepository;

    JourneyServiceImpl(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    @Override
    public JourneyResponse getJourneys(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Journey> journeys = journeyRepository.findAll(pageable);

        List<Journey> listOfJourneys = journeys.getContent();

        List<JourneyDto> content = listOfJourneys.stream()
                .map(JourneyDto::mapToDto)
                .toList();

        JourneyResponse journeyResponse = new JourneyResponse();
        journeyResponse.setContent(content);
        journeyResponse.setPageNo(journeys.getNumber());
        journeyResponse.setPageSize(journeys.getSize());
        journeyResponse.setTotalElements(journeys.getTotalElements());
        journeyResponse.setTotalPages(journeys.getTotalPages());
        journeyResponse.setLast(journeys.isLast());

        return journeyResponse;
    }
}