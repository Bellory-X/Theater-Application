package com.example.theater.services.emploees.troupes;

import com.example.theater.dao.entities.emploees.troupes.MusiciansPerformance;
import com.example.theater.dao.entities.emploees.troupes.MusiciansPerformanceKey;
import com.example.theater.dao.repositories.emploees.troupes.MusiciansPerformanceRepository;
import com.example.theater.dto.emploees.troupes.MusiciansPerformanceDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.troupes.MusiciansPerformanceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class MusiciansPerformanceService {
    private final MusiciansPerformanceRepository repository;
    private final MusiciansPerformanceMapper mapper;

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
        if (!repository.existsById(new MusiciansPerformanceKey(musiciansPerformanceDTO.getIdPerformance(), musiciansPerformanceDTO.getIdEmployee())))
            throw new RecordNotFoundException("Not found " + musiciansPerformanceDTO.getIdPerformance());

        MusiciansPerformance musiciansPerformance = mapper.toMusiciansPerformance(musiciansPerformanceDTO);
        repository.save(musiciansPerformance);
    }

    public void drop(MusiciansPerformanceDTO musiciansPerformanceDTO) {
        if (!repository.existsById(new MusiciansPerformanceKey(musiciansPerformanceDTO.getIdPerformance(), musiciansPerformanceDTO.getIdEmployee())))
            throw new RecordNotFoundException("Not found " + musiciansPerformanceDTO.getIdPerformance());

        MusiciansPerformance musiciansPerformance = mapper.toMusiciansPerformance(musiciansPerformanceDTO);
        repository.delete(musiciansPerformance);
    }
}
