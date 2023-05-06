package com.example.theater.service.employee;

import com.example.theater.dao.entity.employee.Actor;
import com.example.theater.dao.repository.employee.ActorRepository;
import com.example.theater.dto.employee.ActorDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.ActorMapper;
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
        if (!repository.existsById(actorDTO.getIdEmployee()))
            throw new RecordNotFoundException("Not found " + actorDTO.getIdEmployee());

        Actor actor = mapper.toActor(actorDTO);
        repository.save(actor);
    }

    public void drop(ActorDTO actorDTO) {
        if (!repository.existsById(actorDTO.getIdEmployee()))
            throw new RecordNotFoundException("Not found " + actorDTO.getIdEmployee());

        Actor actor = mapper.toActor(actorDTO);
        repository.delete(actor);
    }
}
