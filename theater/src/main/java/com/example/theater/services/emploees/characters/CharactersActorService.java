package com.example.theater.services.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.CharactersActor;
import com.example.theater.dao.entities.emploees.characters.CharactersActorKey;
import com.example.theater.dao.repositories.emploees.charecters.CharactersActorRepository;
import com.example.theater.dto.emploees.characters.CharactersActorDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.characters.CharactersActorMapper;
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

    public void add(CharactersActorDTO charactersActorDTO) {
        CharactersActor charactersActor = mapper.toCharactersActor(charactersActorDTO);
        repository.save(charactersActor);
    }

    public void edit(CharactersActorDTO charactersActorDTO) {
        if (!repository.existsById(new CharactersActorKey(charactersActorDTO.getId(), charactersActorDTO.getCharacter())))
            throw new RecordNotFoundException("Not found " + charactersActorDTO.getId());

        CharactersActor charactersActor = mapper.toCharactersActor(charactersActorDTO);
        repository.save(charactersActor);
    }

    public void drop(CharactersActorDTO charactersActorDTO) {
        if (!repository.existsById(new CharactersActorKey(charactersActorDTO.getId(), charactersActorDTO.getCharacter())))
            throw new RecordNotFoundException("Not found " + charactersActorDTO.getId());

        CharactersActor charactersActor = mapper.toCharactersActor(charactersActorDTO);
        repository.delete(charactersActor);
    }
}
