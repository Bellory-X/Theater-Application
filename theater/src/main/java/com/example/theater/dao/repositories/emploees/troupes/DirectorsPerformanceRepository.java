package com.example.theater.dao.repositories.emploees.troupes;

import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import com.example.theater.dao.entities.emploees.troupes.DirectorsPerformance;
import com.example.theater.dao.entities.emploees.troupes.DirectorsPerformanceKey;
import org.springframework.data.repository.CrudRepository;

public interface DirectorsPerformanceRepository extends CrudRepository<DirectorsPerformance, DirectorsPerformanceKey> {
}