package com.example.theater.service.performance.troupe;

import com.example.theater.dao.entity.performance.troupe.DirectorsPerformance;
import com.example.theater.dao.repository.performance.troupe.DirectorsPerformanceRepository;
import com.example.theater.dto.employee.DirectorDTO;
import com.example.theater.dto.employee.EmployeeDTO;
import com.example.theater.dto.employee.MusicianDTO;
import com.example.theater.dto.performance.troupe.DirectorsPerformanceDTO;
import com.example.theater.exception.QueryException;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.troupe.DirectorsPerformanceMapper;
import com.example.theater.service.Generator;
import com.example.theater.service.employee.DirectorService;
import com.example.theater.service.employee.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DirectorsPerformanceService {
    private final DirectorsPerformanceRepository repository;
    private final DirectorsPerformanceMapper mapper;
    private final DirectorService directorService;

    public Optional<DirectorsPerformanceDTO> getById(int id) {
        return repository.findById(id).map(mapper::toDirectorsPerformanceDTO);
    }

    public List<DirectorDTO> getAllDirector(String theater) {
        return directorService.getAll().stream().filter(el -> el.getTheater().equals(theater)).toList();
    }

    public List<DirectorsPerformanceDTO> getAll() {
        Iterable<DirectorsPerformance> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toDirectorsPerformanceDTO)
                .peek(el -> directorService.getById(el.getIdEmployee()).ifPresent(v -> {
                    el.setFullName(v.getFullName());
                    el.setCategory(v.getCategory());
                }))
                .collect(Collectors.toList());
    }

    public void add(DirectorsPerformanceDTO dto) {
        try {
            dto.setId(Generator.generateId());
            DirectorsPerformance directorsPerformance = mapper.toDirectorsPerformance(dto);
            repository.save(directorsPerformance);
        } catch (DataAccessException e) {
            throw new QueryException("Recheck fields maybe it exists");
        }
    }

    public void edit(DirectorsPerformanceDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getIdPerformance());

        DirectorsPerformance directorsPerformance = mapper.toDirectorsPerformance(dto);
        repository.save(directorsPerformance);
    }

    public void drop(DirectorsPerformanceDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getIdPerformance());

        DirectorsPerformance directorsPerformance = mapper.toDirectorsPerformance(dto);
        repository.delete(directorsPerformance);
    }
}
