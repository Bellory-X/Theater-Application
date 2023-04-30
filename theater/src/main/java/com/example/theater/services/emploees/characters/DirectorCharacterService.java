package com.example.theater.services.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.DirectorCharacter;
import com.example.theater.dao.repositories.emploees.charecters.DirectorCharacterRepository;
import com.example.theater.dto.emploees.characters.DirectorCharacterDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.characters.DirectorCharacterMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DirectorCharacterService {
    DirectorCharacterRepository repository;
    DirectorCharacterMapper mapper;

    public List<DirectorCharacterDTO> getAll() {
        Iterable<DirectorCharacter> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toDirectorCharacterDTO)
                .collect(Collectors.toList());
    }

    public void add(DirectorCharacterDTO directorCharacterDTO) {
        DirectorCharacter directorCharacter = mapper.toDirectorCharacter(directorCharacterDTO);
        repository.save(directorCharacter);
    }

    public void edit(DirectorCharacterDTO directorCharacterDTO) {
        if (!repository.existsById(directorCharacterDTO.getCharacter()))
            throw new RecordNotFoundException("Not Found " + directorCharacterDTO.getCharacter());

        DirectorCharacter directorCharacter = mapper.toDirectorCharacter(directorCharacterDTO);
        repository.save(directorCharacter);
    }

    public void drop(DirectorCharacterDTO directorCharacterDTO) {
        if (!repository.existsById(directorCharacterDTO.getCharacter()))
            throw new RecordNotFoundException("Not Found " + directorCharacterDTO.getCharacter());

        DirectorCharacter directorCharacter = mapper.toDirectorCharacter(directorCharacterDTO);
        repository.delete(directorCharacter);
    }
}
