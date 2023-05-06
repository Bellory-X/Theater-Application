package com.example.theater.dao.repository.employee;

import com.example.theater.dao.entity.employee.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}