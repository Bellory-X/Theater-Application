package com.example.theater.service.performance.play;

import com.example.theater.dao.entity.performance.play.Play;
import com.example.theater.dao.repository.performance.play.PlayRepository;
import com.example.theater.dto.performance.play.PlayDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.play.PlayMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PlayService {
    private final PlayRepository repository;
    private final PlayMapper mapper;

    public Optional<PlayDTO> getById(int id) {
        return repository.findById(id).map(mapper::toPlayDTO);
    }

    public List<PlayDTO> getAll() {
        Iterable<Play> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toPlayDTO)
                .collect(Collectors.toList());
    }

    public void add(PlayDTO dto) {
        dto.setId(Generator.generateId());
        Play play = mapper.toPlay(dto);
        repository.save(play);
    }

    public void edit(PlayDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Play play = mapper.toPlay(dto);
        repository.save(play);
    }

    public void drop(PlayDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Play play = mapper.toPlay(dto);
        repository.delete(play);
    }
}
