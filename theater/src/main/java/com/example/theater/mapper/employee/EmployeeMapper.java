package com.example.theater.mapper.employee;

import com.example.theater.dao.entity.employee.Employee;
import com.example.theater.dto.employee.EmployeeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeDTO employeeDTO);

//    @Mapping(target = "id", source = "isWorker")
    EmployeeDTO toEmployeeDTO(Employee employee);
}
