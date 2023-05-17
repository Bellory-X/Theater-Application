package com.example.theater.service.employee;

import com.example.theater.dao.entity.employee.Worker;
import com.example.theater.dao.repository.employee.WorkerRepository;
import com.example.theater.dto.employee.ActorDTO;
import com.example.theater.dto.employee.EmployeeDTO;
import com.example.theater.dto.employee.WorkerDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.WorkerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public List<WorkerDTO> findActorQuery1(String theater, int exp1, int exp2, String gender, Date birthday1,
                                          Date birthday2, int countChild1, int countChild2, int salary1, int salary2) {
        return repository.findActorQuery1(theater, exp1, exp2, gender, birthday1, birthday2, countChild1, countChild2, salary1, salary2)
                .stream().map(mapper::toWorkerDTO).toList();
    }
}
