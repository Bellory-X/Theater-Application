package com.example.theater.service.employee.character;

import com.example.theater.dao.entity.employee.character.MusicianCharacter;
import com.example.theater.dao.repository.employee.charecter.MusicianCharacterRepository;
import com.example.theater.dto.employee.character.MusicianCharacterDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.character.MusicianCharacterMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class MusicianCharacterService {
    private final MusicianCharacterRepository repository;
    private final MusicianCharacterMapper mapper;

    public Optional<MusicianCharacterDTO> getById(int id) {
        return repository.findById(id).map(mapper::toMusicianCharacterDTO);
    }

    public List<MusicianCharacterDTO> getAll() {
        Iterable<MusicianCharacter> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toMusicianCharacterDTO)
                .collect(Collectors.toList());
    }

    public void add(MusicianCharacterDTO dto) {
        dto.setId(Generator.generateId());
        MusicianCharacter musicianCharacter = mapper.toMusicianCharacter(dto);
        repository.save(musicianCharacter);
    }

    public void edit(MusicianCharacterDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getCharacter());

        MusicianCharacter musicianCharacter = mapper.toMusicianCharacter(dto);
        repository.save(musicianCharacter);
    }

    public void drop(MusicianCharacterDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getCharacter());

        MusicianCharacter musicianCharacter = mapper.toMusicianCharacter(dto);
        repository.delete(musicianCharacter);
    }
}
