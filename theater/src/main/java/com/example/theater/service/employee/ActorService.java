package com.example.theater.service.employee;

import com.example.theater.dao.entity.employee.Actor;
import com.example.theater.dao.entity.employee.Employee;
import com.example.theater.dao.repository.employee.ActorRepository;
import com.example.theater.dto.employee.ActorDTO;
import com.example.theater.dto.employee.EmployeeDTO;
import com.example.theater.dto.employee.MusicianDTO;
import com.example.theater.exception.QueryException;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.ActorMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ActorService {
    private final ActorRepository repository;
    private final ActorMapper mapper;
    private final EmployeeService employeeService;

    public Optional<ActorDTO> getById(int id) {
        return repository.findById(id).map(mapper::toActorDTO);
    }

    public List<ActorDTO> getAll() {
        Iterable<Actor> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toActorDTO)
                .collect(Collectors.toList());
    }

    public void add(ActorDTO dto) {
        try {
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
            Actor actor = mapper.toActor(dto);
            repository.save(actor);
        } catch (DataAccessException e) {
            throw new QueryException("Recheck fields maybe it exists");
        }
    }

    public void edit(ActorDTO dto) {
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
        Actor actor = mapper.toActor(dto);
        repository.save(actor);
    }

    public void drop(ActorDTO dto) {
        if (!repository.existsById(dto.getIdEmployee()))
            throw new RecordNotFoundException("Not found " + dto.getIdEmployee());

        employeeService.drop(EmployeeDTO.builder()
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
        Actor actor = mapper.toActor(dto);
        repository.delete(actor);
    }

    public List<ActorDTO> findActorQuery1(String theater, int exp1, int exp2, String gender, Date birthday1,
                                          Date birthday2, int countChild1, int countChild2, int salary1, int salary2) {
        return repository.findActorQuery1(theater, exp1, exp2, gender, birthday1, birthday2, countChild1, countChild2, salary1, salary2)
                .stream().map(mapper::toActorDTO).toList();
    }

    public List<ActorDTO> findActorQuery2(String theater, Date dataRank1, Date dataRank2, String contest, String gender,
                                       Date birthday) {
        return repository.findActorQuery2(theater, dataRank1, dataRank2, contest, gender, birthday)
                .stream().map(mapper::toActorDTO).toList();
    }
}
