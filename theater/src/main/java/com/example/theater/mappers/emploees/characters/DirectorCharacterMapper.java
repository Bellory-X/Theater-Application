package com.example.theater.mappers.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.DirectorCharacter;
import com.example.theater.dto.emploees.characters.DirectorCharacterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectorCharacterMapper {
    DirectorCharacter toDirectorCharacter(DirectorCharacterDTO directorCharacterDTO);

    DirectorCharacterDTO toDirectorCharacterDTO(DirectorCharacter directorCharacter);
}
