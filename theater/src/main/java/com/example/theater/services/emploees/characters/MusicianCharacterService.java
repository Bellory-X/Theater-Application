package com.example.theater.services.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.MusicianCharacter;
import com.example.theater.dao.repositories.emploees.charecters.MusicianCharacterRepository;
import com.example.theater.dto.emploees.characters.MusicianCharacterDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.characters.MusicianCharacterMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class MusicianCharacterService {
    private final MusicianCharacterRepository repository;
    private final MusicianCharacterMapper mapper;

    public List<MusicianCharacterDTO> getAll() {
        Iterable<MusicianCharacter> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toMusicianCharacterDTO)
                .collect(Collectors.toList());
    }

    public void add(MusicianCharacterDTO musicianCharacterDTO) {
        MusicianCharacter musicianCharacter = mapper.toMusicianCharacter(musicianCharacterDTO);
        repository.save(musicianCharacter);
    }

    public void edit(MusicianCharacterDTO musicianCharacterDTO) {
        if (!repository.existsById(musicianCharacterDTO.getCharacter()))
            throw new RecordNotFoundException("Not found " + musicianCharacterDTO.getCharacter());

        MusicianCharacter musicianCharacter = mapper.toMusicianCharacter(musicianCharacterDTO);
        repository.save(musicianCharacter);
    }

    public void drop(MusicianCharacterDTO musicianCharacterDTO) {
        if (!repository.existsById(musicianCharacterDTO.getCharacter()))
            throw new RecordNotFoundException("Not found " + musicianCharacterDTO.getCharacter());

        MusicianCharacter musicianCharacter = mapper.toMusicianCharacter(musicianCharacterDTO);
        repository.delete(musicianCharacter);
    }
}
