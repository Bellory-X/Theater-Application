package com.example.theater.service.employee;

import com.example.theater.dao.entity.employee.Musician;
import com.example.theater.dao.repository.employee.MusicianRepository;
import com.example.theater.dto.employee.MusicianDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.MusicianMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class MusicianService {
    MusicianRepository repository;
    MusicianMapper mapper;

    public List<MusicianDTO> getAll() {
        Iterable<Musician> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toMusicianDTO)
                .collect(Collectors.toList());
    }

    public void add(MusicianDTO musicianDTO) {
        Musician musician = mapper.toMusician(musicianDTO);
        repository.save(musician);
    }

    public void edit(MusicianDTO musicianDTO) {
        if (!repository.existsById(musicianDTO.getIdEmployee()))
            throw new RecordNotFoundException("Not found " + musicianDTO.getIdEmployee());

        Musician musician = mapper.toMusician(musicianDTO);
        repository.save(musician);
    }

    public void drop(MusicianDTO musicianDTO) {
        if (!repository.existsById(musicianDTO.getIdEmployee()))
            throw new RecordNotFoundException("Not found " + musicianDTO.getIdEmployee());

        Musician musician = mapper.toMusician(musicianDTO);
        repository.delete(musician);
    }
}
