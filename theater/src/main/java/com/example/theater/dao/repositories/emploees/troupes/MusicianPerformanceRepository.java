package com.example.theater.dao.repositories.emploees.troupes;

import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import com.example.theater.dao.entities.emploees.troupes.MusiciansPerformance;
import com.example.theater.dao.entities.emploees.troupes.MusiciansPerformanceKey;
import org.springframework.data.repository.CrudRepository;

public interface MusicianPerformanceRepository extends CrudRepository<MusiciansPerformance, MusiciansPerformanceKey> {
}