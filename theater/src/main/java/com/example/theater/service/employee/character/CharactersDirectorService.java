package com.example.theater.service.employee.character;

import com.example.theater.dao.entity.employee.character.CharactersDirector;
import com.example.theater.dao.repository.employee.charecter.CharactersDirectorRepository;
import com.example.theater.dto.employee.character.CharactersDirectorDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.character.CharactersDirectorMapper;
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
        CharactersDirector charactersDirector = mapper.toNewCharactersDirector(charactersDirectorDTO);
        repository.save(charactersDirector);
    }

    public void edit(CharactersDirectorDTO charactersDirectorDTO) {
        if (!repository.existsById(charactersDirectorDTO.getId()))
            throw new RecordNotFoundException("Not found " + charactersDirectorDTO.getId());

        CharactersDirector charactersDirector = mapper.toCharactersDirector(charactersDirectorDTO);
        repository.save(charactersDirector);
    }

    public void drop(CharactersDirectorDTO charactersDirectorDTO) {
        if (!repository.existsById(charactersDirectorDTO.getId()))
            throw new RecordNotFoundException("Not found " + charactersDirectorDTO.getId());

        CharactersDirector charactersDirector = mapper.toCharactersDirector(charactersDirectorDTO);
        repository.delete(charactersDirector);
    }
}
