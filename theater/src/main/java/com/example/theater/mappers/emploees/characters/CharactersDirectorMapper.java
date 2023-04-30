package com.example.theater.mappers.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.CharactersDirector;
import com.example.theater.dto.emploees.characters.CharactersDirectorDTO;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface CharactersDirectorMapper {
    CharactersDirector toCharactersDirector(CharactersDirectorDTO charactersDirectorDTO);

    CharactersDirectorDTO toCharactersDirectorDTO(CharactersDirector charactersDirector);
}
