package com.example.theater.services;

import com.example.theater.dao.entities.Theater;
import com.example.theater.dao.repositories.TheaterRepository;
import com.example.theater.dto.TheaterDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.TheaterMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class TheaterService {
    private final TheaterRepository theaterRepository;
    private final TheaterMapper mapper;

    TheaterService(TheaterRepository theaterRepository, TheaterMapper mapper) {
        this.theaterRepository = theaterRepository;
        this.mapper = mapper;
    }

    public TheaterDTO getTheaterById(String id) {
        Theater theater = theaterRepository
                .findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Theater not found: id = " + id));

        return mapper.toTheaterDTO(theater);
    }

    public List<TheaterDTO> getAllTheaters() {
        Iterable<Theater> iterable = theaterRepository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toTheaterDTO)
                .collect(Collectors.toList());
    }

    public void addTheater(TheaterDTO theater) {
        Theater theaterEntity = mapper.toTheater(theater);
        theaterRepository.save(theaterEntity);
    }

    public void editTheater(TheaterDTO theater) {
        if (!theaterRepository.existsById(theater.getId()))
            throw new RecordNotFoundException("Theater not found: name = " + theater.getId());

        Theater theaterEntity = mapper.toTheater(theater);
        theaterRepository.save(theaterEntity);
    }

    public void dropTheater(TheaterDTO theater) {
        if (!theaterRepository.existsById(theater.getId()))
            throw new RecordNotFoundException("Theater not found: name = " + theater.getId());

        Theater theaterEntity = mapper.toTheater(theater);
        theaterRepository.delete(theaterEntity);
    }
}
