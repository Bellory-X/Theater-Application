package com.example.theater.services.emploees.troupes;

import com.example.theater.dao.entities.emploees.troupes.DirectorsPerformance;
import com.example.theater.dao.entities.emploees.troupes.DirectorsPerformanceKey;
import com.example.theater.dao.repositories.emploees.troupes.DirectorsPerformanceRepository;
import com.example.theater.dto.emploees.troupes.DirectorsPerformanceDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.troupes.DirectorsPerformanceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DirectorsPerformanceService {
    private final DirectorsPerformanceRepository repository;
    private final DirectorsPerformanceMapper mapper;

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
        if (!repository.existsById(new DirectorsPerformanceKey(directorsPerformanceDTO.getIdPerformance(), directorsPerformanceDTO.getIdEmployee())))
            throw new RecordNotFoundException("Not found " + directorsPerformanceDTO.getIdPerformance());

        DirectorsPerformance directorsPerformance = mapper.toDirectorsPerformance(directorsPerformanceDTO);
        repository.save(directorsPerformance);
    }

    public void drop(DirectorsPerformanceDTO directorsPerformanceDTO) {
        if (!repository.existsById(new DirectorsPerformanceKey(directorsPerformanceDTO.getIdPerformance(), directorsPerformanceDTO.getIdEmployee())))
            throw new RecordNotFoundException("Not found " + directorsPerformanceDTO.getIdPerformance());

        DirectorsPerformance directorsPerformance = mapper.toDirectorsPerformance(directorsPerformanceDTO);
        repository.delete(directorsPerformance);
    }
}
