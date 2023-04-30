package com.example.theater.mappers.emploees;

import com.example.theater.dao.entities.emploees.Employee;
import com.example.theater.dto.emploees.EmployeeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO toEmployeeDTO(Employee employee);
}
