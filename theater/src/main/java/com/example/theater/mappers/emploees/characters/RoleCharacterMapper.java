package com.example.theater.mappers.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.RoleCharacter;
import com.example.theater.dto.emploees.characters.RoleCharacterDTO;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface RoleCharacterMapper {
    RoleCharacter toRoleCharacter(RoleCharacterDTO roleCharacterDTO);

    RoleCharacterDTO toRoleCharacterDTO(RoleCharacter roleCharacter);
}
