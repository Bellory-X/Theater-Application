package com.example.theater.mappers.emploees.troupes;

import com.example.theater.dao.entities.emploees.troupes.RolesActor;
import com.example.theater.dto.emploees.troupes.RolesActorDTO;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface RolesActorMapper {
    RolesActor toRolesActor(RolesActorDTO rolesActorDTO);

    RolesActorDTO toRolesActorDTO(RolesActor rolesActor);
}
