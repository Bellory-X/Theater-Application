package com.example.theater.service.performance;

import com.example.theater.dao.entity.performance.Hall;
import com.example.theater.dao.repository.performance.HallRepository;
import com.example.theater.dto.performance.HallDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.HallMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class HallService {
    private final HallRepository repository;
    private final HallMapper mapper;

    public Optional<HallDTO> getById(int id) {
        return repository.findById(id).map(mapper::toHallDTO);
    }

    public List<HallDTO> getAll() {
        Iterable<Hall> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toHallDTO)
                .collect(Collectors.toList());
    }

    public void add(HallDTO dto) {
        Hall hall = mapper.toHall(dto);
        repository.save(hall);
    }

    public void edit(HallDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Hall hall = mapper.toHall(dto);
        repository.save(hall);
    }

    public void drop(HallDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Hall hall = mapper.toHall(dto);
        repository.delete(hall);
    }
}
