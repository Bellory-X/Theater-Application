package com.example.theater.dao.repository.employee;

import com.example.theater.dao.entity.employee.Worker;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepository extends CrudRepository<Worker, Integer> {
}