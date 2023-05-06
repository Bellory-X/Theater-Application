package com.example.theater.service.performance;

import com.example.theater.dao.entity.performance.Hall;
import com.example.theater.dao.repository.performance.HallRepository;
import com.example.theater.dto.performance.HallDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.HallMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class HallService {
    private final HallRepository repository;
    private final HallMapper mapper;

    public List<HallDTO> getAll() {
        Iterable<Hall> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toHallDTO)
                .collect(Collectors.toList());
    }

    public void add(HallDTO hallDTO) {
        Hall hall = mapper.toHall(hallDTO);
        repository.save(hall);
    }

    public void edit(HallDTO hallDTO) {
        if (!repository.existsById(hallDTO.getId()))
            throw new RecordNotFoundException("Not found " + hallDTO.getId());

        Hall hall = mapper.toHall(hallDTO);
        repository.save(hall);
    }

    public void drop(HallDTO hallDTO) {
        if (!repository.existsById(hallDTO.getId()))
            throw new RecordNotFoundException("Not found " + hallDTO.getId());

        Hall hall = mapper.toHall(hallDTO);
        repository.delete(hall);
    }
}
