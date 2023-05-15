package com.example.theater.service.employee;

import com.example.theater.dao.entity.employee.Worker;
import com.example.theater.dao.repository.employee.WorkerRepository;
import com.example.theater.dto.employee.EmployeeDTO;
import com.example.theater.dto.employee.WorkerDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.WorkerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class WorkerService {
    WorkerRepository repository;
    WorkerMapper mapper;
    private final EmployeeService employeeService;

    public List<WorkerDTO> getAll() {
        Iterable<Worker> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toWorkerDTO)
                .collect(Collectors.toList());
    }

    public void add(WorkerDTO dto) {
        int idEmployee = employeeService.add(EmployeeDTO.builder()
                .fullName(dto.getFullName())
                .experience(dto.getExperience())
                .gender(dto.getGender())
                .birthday(dto.getBirthday())
                .countChild(dto.getCountChild())
                .salary(dto.getSalary())
                .worker(dto.isWorker())
                .theater(dto.getTheater())
                .build());
        dto.setIdEmployee(idEmployee);
        Worker worker = mapper.toWorker(dto);
        repository.save(worker);
    }

    public void edit(WorkerDTO dto) {
        if (!repository.existsById(dto.getIdEmployee()))
            throw new RecordNotFoundException("Not found " + dto.getIdEmployee());

        employeeService.edit(EmployeeDTO.builder()
                .id(dto.getIdEmployee())
                .fullName(dto.getFullName())
                .experience(dto.getExperience())
                .gender(dto.getGender())
                .birthday(dto.getBirthday())
                .countChild(dto.getCountChild())
                .salary(dto.getSalary())
                .worker(dto.isWorker())
                .theater(dto.getTheater())
                .build());
        Worker worker = mapper.toWorker(dto);
        repository.save(worker);
    }

    public void drop(WorkerDTO dto) {
        if (!repository.existsById(dto.getIdEmployee()))
            throw new RecordNotFoundException("Not found " + dto.getIdEmployee());

        employeeService.edit(EmployeeDTO.builder()
                .id(dto.getIdEmployee())
                .fullName(dto.getFullName())
                .experience(dto.getExperience())
                .gender(dto.getGender())
                .birthday(dto.getBirthday())
                .countChild(dto.getCountChild())
                .salary(dto.getSalary())
                .worker(dto.isWorker())
                .theater(dto.getTheater())
                .build());
        Worker worker = mapper.toWorker(dto);
        repository.delete(worker);
    }
}
