package com.example.theater.service.employee.character;

import com.example.theater.dao.entity.employee.character.DirectorCharacter;
import com.example.theater.dao.repository.employee.charecter.DirectorCharacterRepository;
import com.example.theater.dto.employee.character.DirectorCharacterDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.character.DirectorCharacterMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DirectorCharacterService {
    DirectorCharacterRepository repository;
    DirectorCharacterMapper mapper;

    public Optional<DirectorCharacterDTO> getById(int id) {
        return repository.findById(id).map(mapper::toDirectorCharacterDTO);
    }

    public List<DirectorCharacterDTO> getAll() {
        Iterable<DirectorCharacter> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toDirectorCharacterDTO)
                .collect(Collectors.toList());
    }

    public void add(DirectorCharacterDTO dto) {
        dto.setId(Generator.generateId());
        DirectorCharacter directorCharacter = mapper.toDirectorCharacter(dto);
        repository.save(directorCharacter);
    }

    public void edit(DirectorCharacterDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not Found " + dto.getCharacter());

        DirectorCharacter directorCharacter = mapper.toDirectorCharacter(dto);
        repository.save(directorCharacter);
    }

    public void drop(DirectorCharacterDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not Found " + dto.getCharacter());

        DirectorCharacter directorCharacter = mapper.toDirectorCharacter(dto);
        repository.delete(directorCharacter);
    }
}
