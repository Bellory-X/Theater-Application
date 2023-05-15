package com.example.theater.service.employee.character;

import com.example.theater.dao.entity.employee.character.CharactersMusician;
import com.example.theater.dao.repository.employee.charecter.CharactersMusicianRepository;
import com.example.theater.dto.employee.character.CharactersMusicianDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.character.CharactersMusicianMapper;
import com.example.theater.service.Generator;
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

    public void add(CharactersMusicianDTO dto) {
        dto.setId(Generator.generateId());
        CharactersMusician charactersMusician = mapper.toCharactersMusician(dto);
        repository.save(charactersMusician);
    }

    public void edit(CharactersMusicianDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        CharactersMusician charactersMusician = mapper.toCharactersMusician(dto);
        repository.save(charactersMusician);
    }

    public void drop(CharactersMusicianDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        CharactersMusician charactersMusician = mapper.toCharactersMusician(dto);
        repository.delete(charactersMusician);
    }
}
