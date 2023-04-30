package com.example.theater.mappers.emploees;

import com.example.theater.dao.entities.emploees.Actor;
import com.example.theater.dto.emploees.ActorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    Actor toActor(ActorDTO actorDTO);

    ActorDTO toActorDTO(Actor actor);
}
