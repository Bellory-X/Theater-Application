package com.example.theater.service.employee.character;

import com.example.theater.dao.entity.employee.character.CharactersMusician;
import com.example.theater.dao.repository.employee.charecter.CharactersMusicianRepository;
import com.example.theater.dto.employee.character.CharactersMusicianDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.character.CharactersMusicianMapper;
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
        if (!repository.existsById(charactersMusicianDTO.getId()))
            throw new RecordNotFoundException("Not found " + charactersMusicianDTO.getId());

        CharactersMusician charactersMusician = mapper.toCharactersMusician(charactersMusicianDTO);
        repository.save(charactersMusician);
    }

    public void drop(CharactersMusicianDTO charactersMusicianDTO) {
        if (!repository.existsById(charactersMusicianDTO.getId()))
            throw new RecordNotFoundException("Not found " + charactersMusicianDTO.getId());

        CharactersMusician charactersMusician = mapper.toCharactersMusician(charactersMusicianDTO);
        repository.delete(charactersMusician);
    }
}
