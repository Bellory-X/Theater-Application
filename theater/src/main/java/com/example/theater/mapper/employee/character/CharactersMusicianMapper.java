package com.example.theater.mapper.employee.character;

import com.example.theater.dao.entity.employee.character.CharactersMusician;
import com.example.theater.dto.employee.character.CharactersMusicianDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharactersMusicianMapper {
    @Mapping(target = "id", ignore = true)
    CharactersMusician toNewCharactersMusician(CharactersMusicianDTO charactersMusicianDTO);

    CharactersMusician toCharactersMusician(CharactersMusicianDTO charactersMusicianDTO);

    CharactersMusicianDTO toCharactersMusicianDTO(CharactersMusician charactersMusician);
}
