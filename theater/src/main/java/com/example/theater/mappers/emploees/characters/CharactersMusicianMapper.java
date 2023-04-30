package com.example.theater.mappers.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.CharactersMusician;
import com.example.theater.dto.emploees.characters.CharactersMusicianDTO;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface CharactersMusicianMapper {
    CharactersMusician toCharactersMusician(CharactersMusicianDTO charactersMusicianDTO);

    CharactersMusicianDTO toCharactersMusicianDTO(CharactersMusician charactersMusician);
}
