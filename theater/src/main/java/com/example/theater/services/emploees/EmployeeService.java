package com.example.theater.services.emploees;

import com.example.theater.dao.entities.emploees.Employee;
import com.example.theater.dao.repositories.emploees.EmployeeRepository;
import com.example.theater.dto.emploees.EmployeeDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.EmployeeMapper;
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

    public void add(EmployeeDTO employeeDTO) {
        Employee employee = mapper.toEmployee(employeeDTO);
        repository.save(employee);
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
