package com.example.theater.mappers.emploees.troupes;

import com.example.theater.dao.entities.emploees.troupes.RolesActor;
import com.example.theater.dao.entities.emploees.troupes.RolesActorKey;
import com.example.theater.dto.emploees.troupes.RolesActorDTO;
import org.springframework.stereotype.Component;

@Component
public class RolesActorMapperImpl implements RolesActorMapper {
    @Override
    public RolesActor toRolesActor(RolesActorDTO rolesActorDTO) {
        if (rolesActorDTO == null) return null;
        return new RolesActor(new RolesActorKey(rolesActorDTO.getId(), rolesActorDTO.getIdEmployee()));
    }

    @Override
    public RolesActorDTO toRolesActorDTO(RolesActor rolesActor) {
        if (rolesActor == null) return null;
        return new RolesActorDTO(rolesActor.getKey().getId(), rolesActor.getKey().getIdEmployee());
    }
}
