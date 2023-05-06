package com.example.theater.mapper.performance.troupe;

import com.example.theater.dao.entity.performance.troupe.RoleCharacter;
import com.example.theater.dto.performance.troupe.RoleCharacterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleCharacterMapper {
    RoleCharacter toRoleCharacter(RoleCharacterDTO roleCharacterDTO);

    RoleCharacterDTO toRoleCharacterDTO(RoleCharacter roleCharacter);
}
