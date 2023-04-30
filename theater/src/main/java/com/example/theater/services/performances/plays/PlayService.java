package com.example.theater.services.performances.plays;

import com.example.theater.dao.entities.performances.plays.Play;
import com.example.theater.dao.repositories.performances.plays.PlayRepository;
import com.example.theater.dto.performances.plays.PlayDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.performances.plays.PlayMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PlayService {
    private final PlayRepository repository;
    private final PlayMapper mapper;

    public List<PlayDTO> getAll() {
        Iterable<Play> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toPlayDTO)
                .collect(Collectors.toList());
    }

    public void add(PlayDTO playDTO) {
        Play play = mapper.toPlay(playDTO);
        repository.save(play);
    }

    public void edit(PlayDTO playDTO) {
        if (!repository.existsById(playDTO.getId()))
            throw new RecordNotFoundException("Not found " + playDTO.getId());

        Play play = mapper.toPlay(playDTO);
        repository.save(play);
    }

    public void drop(PlayDTO playDTO) {
        if (!repository.existsById(playDTO.getId()))
            throw new RecordNotFoundException("Not found " + playDTO.getId());

        Play play = mapper.toPlay(playDTO);
        repository.delete(play);
    }
}
