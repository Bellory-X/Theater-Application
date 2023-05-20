package com.example.theater.service.performance.troupe;

import com.example.theater.dao.entity.performance.troupe.MusiciansPerformance;
import com.example.theater.dao.repository.performance.troupe.MusiciansPerformanceRepository;
import com.example.theater.dto.performance.troupe.MusiciansPerformanceDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.troupe.MusiciansPerformanceMapper;
import com.example.theater.service.Generator;
import com.example.theater.service.employee.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class MusiciansPerformanceService {
    private final MusiciansPerformanceRepository repository;
    private final MusiciansPerformanceMapper mapper;
    private final EmployeeService employeeService;

    public Optional<MusiciansPerformanceDTO> getById(int id) {
        return repository.findById(id).map(mapper::toMusiciansPerformanceDTO);
    }

    public List<MusiciansPerformanceDTO> getAll() {
        Iterable<MusiciansPerformance> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toMusiciansPerformanceDTO)
                .peek(el -> employeeService.getById(el.getIdEmployee()).ifPresent(v -> el.setFullName(v.getFullName())))
                .collect(Collectors.toList());
    }

    public void add(MusiciansPerformanceDTO dto) {
        dto.setId(Generator.generateId());
        MusiciansPerformance musiciansPerformance = mapper.toMusiciansPerformance(dto);
        repository.save(musiciansPerformance);
    }

    public void edit(MusiciansPerformanceDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getIdPerformance());

        MusiciansPerformance musiciansPerformance = mapper.toMusiciansPerformance(dto);
        repository.save(musiciansPerformance);
    }

    public void drop(MusiciansPerformanceDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getIdPerformance());

        MusiciansPerformance musiciansPerformance = mapper.toMusiciansPerformance(dto);
        repository.delete(musiciansPerformance);
    }
}
