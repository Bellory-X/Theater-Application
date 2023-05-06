package com.example.theater.mapper.employee.character;

import com.example.theater.dao.entity.employee.character.CharactersDirector;
import com.example.theater.dto.employee.character.CharactersDirectorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharactersDirectorMapper {
    CharactersDirector toCharactersDirector(CharactersDirectorDTO charactersDirectorDTO);

    CharactersDirectorDTO toCharactersDirectorDTO(CharactersDirector charactersDirector);
}
