package com.example.theater.dao.repository.performance;

import com.example.theater.dao.entity.performance.Performance;
import org.springframework.data.repository.CrudRepository;

public interface PerformanceRepository extends CrudRepository<Performance, Integer> {
}