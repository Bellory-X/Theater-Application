package com.example.theater.service.employee.character;

import com.example.theater.dao.entity.employee.character.CharactersActor;
import com.example.theater.dao.repository.employee.charecter.CharactersActorRepository;
import com.example.theater.dto.employee.character.CharactersActorDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.character.CharactersActorMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CharactersActorService {
    CharactersActorRepository repository;
    CharactersActorMapper mapper;

    public List<CharactersActorDTO> getAll() {
        Iterable<CharactersActor> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toCharactersActorDTO)
                .collect(Collectors.toList());
    }

    public void add(CharactersActorDTO dto) {
        dto.setId(Generator.generateId());
        CharactersActor charactersActor = mapper.toCharactersActor(dto);
        repository.save(charactersActor);
    }

    public void edit(CharactersActorDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        CharactersActor charactersActor = mapper.toCharactersActor(dto);
        repository.save(charactersActor);
    }

    public void drop(CharactersActorDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        CharactersActor charactersActor = mapper.toCharactersActor(dto);
        repository.delete(charactersActor);
    }
}
