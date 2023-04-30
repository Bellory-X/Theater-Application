package com.example.theater.services.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.CharactersMusician;
import com.example.theater.dao.entities.emploees.characters.CharactersMusicianKey;
import com.example.theater.dao.repositories.emploees.charecters.CharactersMusicianRepository;
import com.example.theater.dto.emploees.characters.CharactersMusicianDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.characters.CharactersMusicianMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CharactersMusicianService {
    CharactersMusicianRepository repository;
    CharactersMusicianMapper mapper;

    public List<CharactersMusicianDTO> getAll() {
        Iterable<CharactersMusician> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toCharactersMusicianDTO)
                .collect(Collectors.toList());
    }

    public void add(CharactersMusicianDTO charactersMusicianDTO) {
        CharactersMusician charactersMusician = mapper.toCharactersMusician(charactersMusicianDTO);
        repository.save(charactersMusician);
    }

    public void edit(CharactersMusicianDTO charactersMusicianDTO) {
        if (!repository.existsById(new CharactersMusicianKey(charactersMusicianDTO.getId(), charactersMusicianDTO.getCharacter())))
            throw new RecordNotFoundException("Not found " + charactersMusicianDTO.getId());

        CharactersMusician charactersMusician = mapper.toCharactersMusician(charactersMusicianDTO);
        repository.save(charactersMusician);
    }

    public void drop(CharactersMusicianDTO charactersMusicianDTO) {
        if (!repository.existsById(new CharactersMusicianKey(charactersMusicianDTO.getId(), charactersMusicianDTO.getCharacter())))
            throw new RecordNotFoundException("Not found " + charactersMusicianDTO.getId());

        CharactersMusician charactersMusician = mapper.toCharactersMusician(charactersMusicianDTO);
        repository.delete(charactersMusician);
    }
}
