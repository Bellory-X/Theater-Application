package com.example.theater.dao.repositories.performances;

import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import com.example.theater.dao.entities.performances.Performance;
import org.springframework.data.repository.CrudRepository;

public interface PerformanceRepository extends CrudRepository<Performance, Integer> {
}