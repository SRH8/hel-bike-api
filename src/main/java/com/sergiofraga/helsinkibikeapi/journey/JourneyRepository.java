package com.sergiofraga.helsinkibikeapi.journey;

import com.sergiofraga.helsinkibikeapi.journey.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Journeys Jpa Repository. It contains the APIs for basic CRUD operations, the APIS for pagination, and the APIs for sorting
 */
public interface JourneyRepository extends JpaRepository<Journey, Integer> {
}