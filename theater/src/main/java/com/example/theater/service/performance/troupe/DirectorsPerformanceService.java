package com.example.theater.service.performance.troupe;

import com.example.theater.dao.entity.performance.troupe.DirectorsPerformance;
import com.example.theater.dao.repository.performance.troupe.DirectorsPerformanceRepository;
import com.example.theater.dto.performance.troupe.DirectorsPerformanceDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.troupe.DirectorsPerformanceMapper;
import lombok.AllArgsConstructor;
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

    public Optional<DirectorsPerformanceDTO> getById(int id) {
        return repository.findById(id).map(mapper::toDirectorsPerformanceDTO);
    }

    public List<DirectorsPerformanceDTO> getAll() {
        Iterable<DirectorsPerformance> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toDirectorsPerformanceDTO)
                .collect(Collectors.toList());
    }

    public void add(DirectorsPerformanceDTO directorsPerformanceDTO) {
        DirectorsPerformance directorsPerformance = mapper.toDirectorsPerformance(directorsPerformanceDTO);
        repository.save(directorsPerformance);
    }

    public void edit(DirectorsPerformanceDTO directorsPerformanceDTO) {
        if (!repository.existsById(directorsPerformanceDTO.getId()))
            throw new RecordNotFoundException("Not found " + directorsPerformanceDTO.getIdPerformance());

        DirectorsPerformance directorsPerformance = mapper.toDirectorsPerformance(directorsPerformanceDTO);
        repository.save(directorsPerformance);
    }

    public void drop(DirectorsPerformanceDTO directorsPerformanceDTO) {
        if (!repository.existsById(directorsPerformanceDTO.getId()))
            throw new RecordNotFoundException("Not found " + directorsPerformanceDTO.getIdPerformance());

        DirectorsPerformance directorsPerformance = mapper.toDirectorsPerformance(directorsPerformanceDTO);
        repository.delete(directorsPerformance);
    }
}
