package com.example.theater.mapper.employee.character;

import com.example.theater.dao.entity.employee.character.DirectorCharacter;
import com.example.theater.dto.employee.character.DirectorCharacterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectorCharacterMapper {
    DirectorCharacter toDirectorCharacter(DirectorCharacterDTO directorCharacterDTO);

    DirectorCharacterDTO toDirectorCharacterDTO(DirectorCharacter directorCharacter);
}
