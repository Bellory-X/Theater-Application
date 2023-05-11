package com.example.theater.service.employee;

import com.example.theater.dao.entity.employee.Director;
import com.example.theater.dao.repository.employee.DirectorRepository;
import com.example.theater.dto.employee.DirectorDTO;
import com.example.theater.dto.employee.EmployeeDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.DirectorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DirectorService {
    DirectorRepository repository;
    DirectorMapper mapper;
    private final EmployeeService employeeService;

    public List<DirectorDTO> getAll() {
        Iterable<Director> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toDirectorDTO)
                .collect(Collectors.toList());
    }

    public void add(DirectorDTO directorDTO) {
        int idEmployee = employeeService.add(EmployeeDTO.builder()
                .fullName(directorDTO.getFullName())
                .experience(directorDTO.getExperience())
                .gender(directorDTO.getGender())
                .birthday(directorDTO.getBirthday())
                .countChild(directorDTO.getCountChild())
                .salary(directorDTO.getSalary())
                .worker(directorDTO.isWorker())
                .theater(directorDTO.getTheater())
                .build());
        directorDTO.setIdEmployee(idEmployee);
        Director director = mapper.toDirector(directorDTO);
        repository.save(director);
    }

    public void edit(DirectorDTO directorDTO) {
        if (!repository.existsById(directorDTO.getIdEmployee()))
            throw new RecordNotFoundException("Not found " + directorDTO.getIdEmployee());

        Director director = mapper.toDirector(directorDTO);
        repository.save(director);
    }

    public void drop(DirectorDTO directorDTO) {
        if (!repository.existsById(directorDTO.getIdEmployee()))
            throw new RecordNotFoundException("Not found " + directorDTO.getIdEmployee());

        Director director = mapper.toDirector(directorDTO);
        repository.delete(director);
    }
}
