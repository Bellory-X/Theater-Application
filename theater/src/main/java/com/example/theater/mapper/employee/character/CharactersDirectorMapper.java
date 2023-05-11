package com.example.theater.mapper.employee.character;

import com.example.theater.dao.entity.employee.character.CharactersDirector;
import com.example.theater.dto.employee.character.CharactersDirectorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharactersDirectorMapper {
    @Mapping(target = "id", ignore = true)
    CharactersDirector toNewCharactersDirector(CharactersDirectorDTO charactersDirectorDTO);

    CharactersDirector toCharactersDirector(CharactersDirectorDTO charactersDirectorDTO);

    CharactersDirectorDTO toCharactersDirectorDTO(CharactersDirector charactersDirector);
}
