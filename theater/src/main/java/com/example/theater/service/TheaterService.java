package com.example.theater.service;

import com.example.theater.dao.entity.Theater;
import com.example.theater.dao.repository.TheaterRepository;
import com.example.theater.dto.TheaterDTO;
import com.example.theater.exception.QueryException;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.TheaterMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class TheaterService {
    private final TheaterRepository repository;
    private final TheaterMapper mapper;

    TheaterService(TheaterRepository theaterRepository, TheaterMapper mapper) {
        this.repository = theaterRepository;
        this.mapper = mapper;
    }

    public List<TheaterDTO> getAll() {
        Iterable<Theater> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toTheaterDTO)
                .collect(Collectors.toList());
    }

    public void add(TheaterDTO theaterDTO) {
        Theater theaterEntity = mapper.toNewTheater(theaterDTO);
        repository.save(theaterEntity);
    }

    public void edit(TheaterDTO theaterDTO) {
        if (!repository.existsById(theaterDTO.getId()))
            throw new QueryException("Not found");

        Theater theaterEntity = mapper.toTheater(theaterDTO);
        repository.save(theaterEntity);
    }

    public void drop(TheaterDTO theaterDTO) {
        if (!repository.existsById(theaterDTO.getId()))
            throw new QueryException("Not found");

        Theater theaterEntity = mapper.toTheater(theaterDTO);
        repository.delete(theaterEntity);
    }
}
