package com.example.theater.service.performance.troupe;

import com.example.theater.dao.entity.performance.troupe.MusiciansPerformance;
import com.example.theater.dao.repository.performance.troupe.MusiciansPerformanceRepository;
import com.example.theater.dto.performance.troupe.MusiciansPerformanceDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.troupe.MusiciansPerformanceMapper;
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

    public Optional<MusiciansPerformanceDTO> getById(int id) {
        return repository.findById(id).map(mapper::toMusiciansPerformanceDTO);
    }

    public List<MusiciansPerformanceDTO> getAll() {
        Iterable<MusiciansPerformance> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toMusiciansPerformanceDTO)
                .collect(Collectors.toList());
    }

    public void add(MusiciansPerformanceDTO musiciansPerformanceDTO) {
        MusiciansPerformance musiciansPerformance = mapper.toMusiciansPerformance(musiciansPerformanceDTO);
        repository.save(musiciansPerformance);
    }

    public void edit(MusiciansPerformanceDTO musiciansPerformanceDTO) {
        if (!repository.existsById(musiciansPerformanceDTO.getId()))
            throw new RecordNotFoundException("Not found " + musiciansPerformanceDTO.getIdPerformance());

        MusiciansPerformance musiciansPerformance = mapper.toMusiciansPerformance(musiciansPerformanceDTO);
        repository.save(musiciansPerformance);
    }

    public void drop(MusiciansPerformanceDTO musiciansPerformanceDTO) {
        if (!repository.existsById(musiciansPerformanceDTO.getId()))
            throw new RecordNotFoundException("Not found " + musiciansPerformanceDTO.getIdPerformance());

        MusiciansPerformance musiciansPerformance = mapper.toMusiciansPerformance(musiciansPerformanceDTO);
        repository.delete(musiciansPerformance);
    }
}
