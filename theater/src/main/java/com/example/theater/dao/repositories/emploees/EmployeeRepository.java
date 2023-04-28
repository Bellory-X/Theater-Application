package com.example.theater.dao.repositories.emploees;

import com.example.theater.dao.entities.emploees.Employee;
import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}