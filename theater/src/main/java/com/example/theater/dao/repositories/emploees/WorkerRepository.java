package com.example.theater.dao.repositories.emploees;

import com.example.theater.dao.entities.emploees.Worker;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepository extends CrudRepository<Worker, Integer> {
}