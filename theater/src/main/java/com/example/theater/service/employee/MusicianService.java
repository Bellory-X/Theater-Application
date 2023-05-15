package com.example.theater.service.employee;

import com.example.theater.dao.entity.employee.Musician;
import com.example.theater.dao.repository.employee.MusicianRepository;
import com.example.theater.dto.employee.ActorDTO;
import com.example.theater.dto.employee.EmployeeDTO;
import com.example.theater.dto.employee.MusicianDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.MusicianMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class MusicianService {
    MusicianRepository repository;
    MusicianMapper mapper;
    private final EmployeeService employeeService;

    public List<MusicianDTO> getAll() {
        Iterable<Musician> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toMusicianDTO)
                .collect(Collectors.toList());
    }

    public void add(MusicianDTO dto) {
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
        Musician musician = mapper.toMusician(dto);
        repository.save(musician);
    }

    public void edit(MusicianDTO dto) {
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
        Musician musician = mapper.toMusician(dto);
        repository.save(musician);
    }

    public void drop(MusicianDTO dto) {
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
        Musician musician = mapper.toMusician(dto);
        repository.delete(musician);
    }

    public List<MusicianDTO> findActorQuery1(String theater, int exp1, int exp2, String gender, Date birthday1,
                                          Date birthday2, int countChild1, int countChild2, int salary1, int salary2) {
        return repository.findActorQuery1(theater, exp1, exp2, gender, birthday1, birthday2, countChild1, countChild2, salary1, salary2)
                .stream().map(mapper::toMusicianDTO).toList();
    }
}
