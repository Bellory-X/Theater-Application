package com.example.theater.mappers.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.CharactersDirector;
import com.example.theater.dao.entities.emploees.characters.CharactersDirectorKey;
import com.example.theater.dto.emploees.characters.CharactersDirectorDTO;
import org.springframework.stereotype.Component;

@Component
public class CharactersDirectorMapperImpl implements CharactersDirectorMapper {
    @Override
    public CharactersDirector toCharactersDirector(CharactersDirectorDTO charactersDirectorDTO) {
        if (charactersDirectorDTO == null) return null;
        return new CharactersDirector(new CharactersDirectorKey(charactersDirectorDTO.getId(), charactersDirectorDTO.getCharacter()));
    }

    @Override
    public CharactersDirectorDTO toCharactersDirectorDTO(CharactersDirector charactersDirector) {
        if (charactersDirector == null) return null;
        return new CharactersDirectorDTO(charactersDirector.getKey().getId(), charactersDirector.getKey().getCharacter());
    }
}
