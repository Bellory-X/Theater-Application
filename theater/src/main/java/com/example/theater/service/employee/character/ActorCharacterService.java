package com.example.theater.service.employee.character;

import com.example.theater.dao.entity.employee.character.ActorCharacter;
import com.example.theater.dao.repository.employee.charecter.ActorCharacterRepository;
import com.example.theater.dto.employee.character.ActorCharacterDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.character.ActorCharacterMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ActorCharacterService {
    private final ActorCharacterRepository repository;
    private final ActorCharacterMapper mapper;

    public List<ActorCharacterDTO> getAll() {
        Iterable<ActorCharacter> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toActorCharacterDTO)
                .collect(Collectors.toList());
    }

    public void add(ActorCharacterDTO actorCharacterDTO) {
        ActorCharacter actorCharacter = mapper.toActorCharacter(actorCharacterDTO);
        repository.save(actorCharacter);
    }

    public void edit(ActorCharacterDTO actorCharacterDTO) {
        if (!repository.existsById(actorCharacterDTO.getId()))
            throw new RecordNotFoundException("Not found " + actorCharacterDTO.getCharacter());

        ActorCharacter actorCharacter = mapper.toActorCharacter(actorCharacterDTO);
        repository.save(actorCharacter);
    }

    public void drop(ActorCharacterDTO actorCharacterDTO) {
        if (!repository.existsById(actorCharacterDTO.getId()))
            throw new RecordNotFoundException("Not found " + actorCharacterDTO.getCharacter());

        ActorCharacter actorCharacter = mapper.toActorCharacter(actorCharacterDTO);
        repository.delete(actorCharacter);
    }
}
