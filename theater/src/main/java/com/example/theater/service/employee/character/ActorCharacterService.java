package com.example.theater.service.employee.character;

import com.example.theater.dao.entity.employee.character.ActorCharacter;
import com.example.theater.dao.repository.employee.charecter.ActorCharacterRepository;
import com.example.theater.dto.employee.character.ActorCharacterDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.character.ActorCharacterMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ActorCharacterService {
    private final ActorCharacterRepository repository;
    private final ActorCharacterMapper mapper;

    public Optional<ActorCharacterDTO> getById(int id) {
        return repository.findById(id).map(mapper::toActorCharacterDTO);
    }

    public List<ActorCharacterDTO> getAll() {
        Iterable<ActorCharacter> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toActorCharacterDTO)
                .collect(Collectors.toList());
    }

    public void add(ActorCharacterDTO dto) {
        dto.setId(Generator.generateId());
        ActorCharacter character = mapper.toActorCharacter(dto);
        repository.save(character);
    }

    public void edit(ActorCharacterDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getCharacter());

        ActorCharacter character = mapper.toActorCharacter(dto);
        repository.save(character);
    }

    public void drop(ActorCharacterDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getCharacter());

        ActorCharacter character = mapper.toActorCharacter(dto);
        repository.delete(character);
    }
}
