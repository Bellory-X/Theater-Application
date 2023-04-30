package com.example.theater.services;

import com.example.theater.dao.entities.Theater;
import com.example.theater.dao.repositories.TheaterRepository;
import com.example.theater.dto.TheaterDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.TheaterMapper;
import lombok.AllArgsConstructor;
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

    public TheaterDTO getById(String id) {
        Theater theater = repository
                .findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Theater not found: id = " + id));

        return mapper.toTheaterDTO(theater);
    }

    public List<TheaterDTO> getAll() {
        Iterable<Theater> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toTheaterDTO)
                .collect(Collectors.toList());
    }

    public void add(TheaterDTO theaterDTO) {
        Theater theaterEntity = mapper.toTheater(theaterDTO);
        repository.save(theaterEntity);
    }

    public void edit(TheaterDTO theaterDTO) {
        if (!repository.existsById(theaterDTO.getId()))
            throw new RecordNotFoundException("Not found " + theaterDTO.getId());

        Theater theaterEntity = mapper.toTheater(theaterDTO);
        repository.save(theaterEntity);
    }

    public void drop(TheaterDTO theaterDTO) {
        if (!repository.existsById(theaterDTO.getId()))
            throw new RecordNotFoundException("Not found " + theaterDTO.getId());

        Theater theaterEntity = mapper.toTheater(theaterDTO);
        repository.delete(theaterEntity);
    }
}
