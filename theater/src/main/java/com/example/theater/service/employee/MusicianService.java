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

    public void add(MusicianDTO musicianDTO) {
        int idEmployee = employeeService.add(EmployeeDTO.builder()
                .fullName(musicianDTO.getFullName())
                .experience(musicianDTO.getExperience())
                .gender(musicianDTO.getGender())
                .birthday(musicianDTO.getBirthday())
                .countChild(musicianDTO.getCountChild())
                .salary(musicianDTO.getSalary())
                .worker(musicianDTO.isWorker())
                .theater(musicianDTO.getTheater())
                .build());
        musicianDTO.setIdEmployee(idEmployee);
        Musician musician = mapper.toMusician(musicianDTO);
        repository.save(musician);
    }

    public void edit(MusicianDTO musicianDTO) {
        if (!repository.existsById(musicianDTO.getIdEmployee()))
            throw new RecordNotFoundException("Not found " + musicianDTO.getIdEmployee());

        Musician musician = mapper.toMusician(musicianDTO);
        repository.save(musician);
    }

    public void drop(MusicianDTO musicianDTO) {
        if (!repository.existsById(musicianDTO.getIdEmployee()))
            throw new RecordNotFoundException("Not found " + musicianDTO.getIdEmployee());

        Musician musician = mapper.toMusician(musicianDTO);
        repository.delete(musician);
    }

    public List<MusicianDTO> findActorQuery1(String theater, int exp1, int exp2, String gender, Date birthday1,
                                          Date birthday2, int countChild1, int countChild2, int salary1, int salary2) {
        return repository.findActorQuery1(theater, exp1, exp2, gender, birthday1, birthday2, countChild1, countChild2, salary1, salary2)
                .stream().map(mapper::toMusicianDTO).toList();
    }
}
