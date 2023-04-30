package com.example.theater.mappers.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.CharactersMusician;
import com.example.theater.dao.entities.emploees.characters.CharactersMusicianKey;
import com.example.theater.dto.emploees.characters.CharactersMusicianDTO;
import org.springframework.stereotype.Component;

@Component
public class CharactersMusicianMapperImpl implements CharactersMusicianMapper {
    @Override
    public CharactersMusician toCharactersMusician(CharactersMusicianDTO charactersMusicianDTO) {
        if (charactersMusicianDTO == null) return null;
        return new CharactersMusician(new CharactersMusicianKey(charactersMusicianDTO.getId(), charactersMusicianDTO.getCharacter()));
    }

    @Override
    public CharactersMusicianDTO toCharactersMusicianDTO(CharactersMusician charactersMusician) {
        if (charactersMusician == null) return null;
        return new CharactersMusicianDTO(charactersMusician.getKey().getId(), charactersMusician.getKey().getCharacter());
    }
}
