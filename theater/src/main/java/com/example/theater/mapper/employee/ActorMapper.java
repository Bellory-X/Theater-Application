package com.example.theater.mapper.employee;

import com.example.theater.dao.entity.employee.Actor;
import com.example.theater.dto.employee.ActorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    Actor toActor(ActorDTO actorDTO);

    ActorDTO toActorDTO(Actor actor);
}
