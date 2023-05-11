package com.example.theater.mapper.employee;

import com.example.theater.dao.entity.employee.Employee;
import com.example.theater.dto.employee.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "id", ignore = true)
    Employee toNewEmployee(EmployeeDTO employeeDTO);

    Employee toEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO toEmployeeDTO(Employee employee);
}
