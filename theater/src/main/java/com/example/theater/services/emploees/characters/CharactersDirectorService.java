package com.example.theater.services.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.CharactersDirector;
import com.example.theater.dao.entities.emploees.characters.CharactersDirectorKey;
import com.example.theater.dao.repositories.emploees.charecters.CharactersDirectorRepository;
import com.example.theater.dto.emploees.characters.CharactersDirectorDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.characters.CharactersDirectorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CharactersDirectorService {
    CharactersDirectorRepository repository;
    CharactersDirectorMapper mapper;

    public List<CharactersDirectorDTO> getAll() {
        Iterable<CharactersDirector> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toCharactersDirectorDTO)
                .collect(Collectors.toList());
    }

    public void add(CharactersDirectorDTO charactersDirectorDTO) {
        CharactersDirector charactersDirector = mapper.toCharactersDirector(charactersDirectorDTO);
        repository.save(charactersDirector);
    }

    public void edit(CharactersDirectorDTO charactersDirectorDTO) {
        if (!repository.existsById(new CharactersDirectorKey(charactersDirectorDTO.getId(), charactersDirectorDTO.getCharacter())))
            throw new RecordNotFoundException("Not found " + charactersDirectorDTO.getId());

        CharactersDirector charactersDirector = mapper.toCharactersDirector(charactersDirectorDTO);
        repository.save(charactersDirector);
    }

    public void drop(CharactersDirectorDTO charactersDirectorDTO) {
        if (!repository.existsById(new CharactersDirectorKey(charactersDirectorDTO.getId(), charactersDirectorDTO.getCharacter())))
            throw new RecordNotFoundException("Not found " + charactersDirectorDTO.getId());

        CharactersDirector charactersDirector = mapper.toCharactersDirector(charactersDirectorDTO);
        repository.delete(charactersDirector);
    }
}
