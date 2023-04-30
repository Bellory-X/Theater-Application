package com.example.theater.mappers.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.RoleCharacter;
import com.example.theater.dao.entities.emploees.characters.RoleCharacterKey;
import com.example.theater.dto.emploees.characters.RoleCharacterDTO;
import org.springframework.stereotype.Component;

@Component
public class RoleCharacterMapperImpl implements RoleCharacterMapper {
    @Override
    public RoleCharacter toRoleCharacter(RoleCharacterDTO roleCharacterDTO) {
        if (roleCharacterDTO == null) return null;
        return new RoleCharacter(new RoleCharacterKey(roleCharacterDTO.getId(), roleCharacterDTO.getCharacter()));
    }

    @Override
    public RoleCharacterDTO toRoleCharacterDTO(RoleCharacter roleCharacter) {
        if (roleCharacter == null) return null;
        return new RoleCharacterDTO(roleCharacter.getKey().getId(), roleCharacter.getKey().getCharacter());
    }
}
