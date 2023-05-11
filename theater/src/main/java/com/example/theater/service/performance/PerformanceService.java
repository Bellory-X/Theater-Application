package com.example.theater.service.performance;

import com.example.theater.dao.entity.performance.Performance;
import com.example.theater.dao.repository.performance.PerformanceRepository;
import com.example.theater.dto.performance.PerformanceDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.PerformanceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PerformanceService {
    private final PerformanceRepository repository;
    private final PerformanceMapper mapper;

    public Optional<PerformanceDTO> getById(int id) {
        return repository.findById(id).map(mapper::toPerformanceDTO);
    }

    public List<PerformanceDTO> getAll() {
        Iterable<Performance> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toPerformanceDTO)
                .collect(Collectors.toList());
    }

    public void add(PerformanceDTO performanceDTO) {
        Performance performance = mapper.toPerformance(performanceDTO);
        repository.save(performance);
    }

    public void edit(PerformanceDTO performanceDTO) {
        if (!repository.existsById(performanceDTO.getId()))
            throw new RecordNotFoundException("Not found " + performanceDTO.getId());

        Performance performance = mapper.toPerformance(performanceDTO);
        repository.save(performance);
    }

    public void drop(PerformanceDTO performanceDTO) {
        if (!repository.existsById(performanceDTO.getId()))
            throw new RecordNotFoundException("Not found " + performanceDTO.getId());

        Performance performance = mapper.toPerformance(performanceDTO);
        repository.delete(performance);
    }
}
