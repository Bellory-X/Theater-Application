package com.example.theater.services.emploees;

import com.example.theater.dao.entities.emploees.Actor;
import com.example.theater.dao.repositories.emploees.ActorRepository;
import com.example.theater.dto.emploees.ActorDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.ActorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ActorService {
    private final ActorRepository repository;
    private final ActorMapper mapper;

    public List<ActorDTO> getAll() {
        Iterable<Actor> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toActorDTO)
                .collect(Collectors.toList());
    }

    public void add(ActorDTO actorDTO) {
        Actor actor = mapper.toActor(actorDTO);
        repository.save(actor);
    }

    public void edit(ActorDTO actorDTO) {
        if (!repository.existsById(actorDTO.getId()))
            throw new RecordNotFoundException("Not found " + actorDTO.getId());

        Actor actor = mapper.toActor(actorDTO);
        repository.save(actor);
    }

    public void drop(ActorDTO actorDTO) {
        if (!repository.existsById(actorDTO.getId()))
            throw new RecordNotFoundException("Not found " + actorDTO.getId());

        Actor actor = mapper.toActor(actorDTO);
        repository.delete(actor);
    }
}
