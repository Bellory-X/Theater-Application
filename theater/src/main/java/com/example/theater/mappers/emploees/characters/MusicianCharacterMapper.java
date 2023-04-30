package com.example.theater.mappers.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.MusicianCharacter;
import com.example.theater.dto.emploees.characters.MusicianCharacterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicianCharacterMapper {
    MusicianCharacter toMusicianCharacter(MusicianCharacterDTO musicianCharacterDTO);

    MusicianCharacterDTO toMusicianCharacterDTO(MusicianCharacter musicianCharacter);
}
