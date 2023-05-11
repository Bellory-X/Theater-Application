package com.example.theater.service.employee;

import com.example.theater.dao.entity.employee.Employee;
import com.example.theater.dao.repository.employee.EmployeeRepository;
import com.example.theater.dto.employee.EmployeeDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public List<EmployeeDTO> getAll() {
        Iterable<Employee> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    public int add(EmployeeDTO employeeDTO) {
        Employee employee = mapper.toNewEmployee(employeeDTO);
        repository.save(employee);

        return employee.getId();
    }

    public void edit(EmployeeDTO employeeDTO) {
        if (!repository.existsById(employeeDTO.getId()))
            throw new RecordNotFoundException("Not found " + employeeDTO.getId());

        Employee employee = mapper.toEmployee(employeeDTO);
        repository.save(employee);
    }

    public void drop(EmployeeDTO employeeDTO) {
        if (!repository.existsById(employeeDTO.getId()))
            throw new RecordNotFoundException("Not found " + employeeDTO.getId());

        Employee employee = mapper.toEmployee(employeeDTO);
        repository.delete(employee);
    }
}
