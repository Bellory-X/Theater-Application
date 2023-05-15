package com.example.theater.service.employee.character;

import com.example.theater.dao.entity.employee.character.CharactersDirector;
import com.example.theater.dao.repository.employee.charecter.CharactersDirectorRepository;
import com.example.theater.dto.employee.character.CharactersDirectorDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.character.CharactersDirectorMapper;
import com.example.theater.service.Generator;
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

    public void add(CharactersDirectorDTO dto) {
        dto.setId(Generator.generateId());
        CharactersDirector charactersDirector = mapper.toCharactersDirector(dto);
        repository.save(charactersDirector);
    }

    public void edit(CharactersDirectorDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        CharactersDirector charactersDirector = mapper.toCharactersDirector(dto);
        repository.save(charactersDirector);
    }

    public void drop(CharactersDirectorDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        CharactersDirector charactersDirector = mapper.toCharactersDirector(dto);
        repository.delete(charactersDirector);
    }
}
