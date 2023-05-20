package com.example.theater.service.employee;

import com.example.theater.dao.entity.employee.Employee;
import com.example.theater.dao.repository.employee.EmployeeRepository;
import com.example.theater.dto.employee.EmployeeDTO;
import com.example.theater.dto.employee.WorkerDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.EmployeeMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public Optional<EmployeeDTO> getById(int id) {
        return repository.findById(id).map(mapper::toEmployeeDTO);
    }

    public List<EmployeeDTO> getAll() {
        Iterable<Employee> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    public int add(EmployeeDTO dto) {
        dto.setId(Generator.generateId());
        Employee employee = mapper.toEmployee(dto);
        repository.save(employee);

        return employee.getId();
    }

    public void edit(EmployeeDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Employee employee = mapper.toEmployee(dto);
        repository.save(employee);
    }

    public void drop(EmployeeDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Employee employee = mapper.toEmployee(dto);
        repository.delete(employee);
    }

    public List<EmployeeDTO> findActorQuery1(String theater, int exp1, int exp2, String gender, Date birthday1,
                                           Date birthday2, int countChild1, int countChild2, int salary1, int salary2) {
        return repository.findActorQuery1(theater, exp1, exp2, gender, birthday1, birthday2, countChild1, countChild2, salary1, salary2)
                .stream().map(mapper::toEmployeeDTO).toList();
    }
}
