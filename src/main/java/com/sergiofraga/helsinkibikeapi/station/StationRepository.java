package com.sergiofraga.helsinkibikeapi.station;

import com.sergiofraga.helsinkibikeapi.station.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Stations Jpa Repository. It contains the APIs for basic CRUD operations, the APIS for pagination, and the APIs for sorting
 */
public interface StationRepository extends JpaRepository<Station, Integer> {
}