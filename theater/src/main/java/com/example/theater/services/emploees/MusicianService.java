package com.example.theater.services.emploees;

import com.example.theater.dao.entities.emploees.Musician;
import com.example.theater.dao.repositories.emploees.MusicianRepository;
import com.example.theater.dto.emploees.MusicianDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.MusicianMapper;
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
        if (!repository.existsById(musicianDTO.getId()))
            throw new RecordNotFoundException("Not found " + musicianDTO.getId());

        Musician musician = mapper.toMusician(musicianDTO);
        repository.save(musician);
    }

    public void drop(MusicianDTO musicianDTO) {
        if (!repository.existsById(musicianDTO.getId()))
            throw new RecordNotFoundException("Not found " + musicianDTO.getId());

        Musician musician = mapper.toMusician(musicianDTO);
        repository.delete(musician);
    }
}
