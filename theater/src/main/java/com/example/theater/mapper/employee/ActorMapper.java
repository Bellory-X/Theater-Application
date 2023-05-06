package com.example.theater.mapper.employee;

import com.example.theater.dao.entity.employee.Actor;
import com.example.theater.dto.employee.ActorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    @Mapping(target = "employee", ignore = true)
    Actor toActor(ActorDTO actorDTO);

    @Mapping(target = "fullName", source = "employee.fullName")
    @Mapping(target = "experience", source = "employee.experience")
    @Mapping(target = "gender", source = "employee.gender")
    @Mapping(target = "birthday", source = "employee.birthday")
    @Mapping(target = "countChild", source = "employee.countChild")
    @Mapping(target = "salary", source = "employee.salary")
    @Mapping(target = "worker", source = "employee.worker")
    @Mapping(target = "theater", source = "employee.theater")
    ActorDTO toActorDTO(Actor actor);
}
