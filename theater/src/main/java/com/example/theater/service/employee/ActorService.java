package com.example.theater.service.employee;

import com.example.theater.dao.entity.employee.Actor;
import com.example.theater.dao.entity.employee.Employee;
import com.example.theater.dao.repository.employee.ActorRepository;
import com.example.theater.dto.employee.ActorDTO;
import com.example.theater.dto.employee.EmployeeDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.ActorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ActorService {
    private final ActorRepository repository;
    private final ActorMapper mapper;
    private final EmployeeService employeeService;

    public List<ActorDTO> getAll() {
        Iterable<Actor> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toActorDTO)
                .collect(Collectors.toList());
    }

    public void add(ActorDTO actorDTO) {
        int idEmployee = employeeService.add(EmployeeDTO.builder()
                .fullName(actorDTO.getFullName())
                .experience(actorDTO.getExperience())
                .gender(actorDTO.getGender())
                .birthday(actorDTO.getBirthday())
                .countChild(actorDTO.getCountChild())
                .salary(actorDTO.getSalary())
                .worker(actorDTO.isWorker())
                .theater(actorDTO.getTheater())
                .build());
        actorDTO.setIdEmployee(idEmployee);
        Actor actor = mapper.toActor(actorDTO);
        repository.save(actor);
    }

    public void edit(ActorDTO actorDTO) {
        if (!repository.existsById(actorDTO.getIdEmployee()))
            throw new RecordNotFoundException("Not found " + actorDTO.getIdEmployee());

        Actor actor = mapper.toActor(actorDTO);
        repository.save(actor);
    }

    public void drop(ActorDTO actorDTO) {
        if (!repository.existsById(actorDTO.getIdEmployee()))
            throw new RecordNotFoundException("Not found " + actorDTO.getIdEmployee());

        Actor actor = mapper.toActor(actorDTO);
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
