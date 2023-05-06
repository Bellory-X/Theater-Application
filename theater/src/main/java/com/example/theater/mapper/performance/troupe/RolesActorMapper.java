package com.example.theater.mapper.performance.troupe;

import com.example.theater.dao.entity.performance.troupe.RolesActor;
import com.example.theater.dto.performance.troupe.RolesActorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolesActorMapper {
    RolesActor toRolesActor(RolesActorDTO rolesActorDTO);

    RolesActorDTO toRolesActorDTO(RolesActor rolesActor);
}
