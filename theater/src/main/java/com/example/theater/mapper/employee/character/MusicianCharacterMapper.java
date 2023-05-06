package com.example.theater.mapper.employee.character;

import com.example.theater.dao.entity.employee.character.MusicianCharacter;
import com.example.theater.dto.employee.character.MusicianCharacterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicianCharacterMapper {
    MusicianCharacter toMusicianCharacter(MusicianCharacterDTO musicianCharacterDTO);

    MusicianCharacterDTO toMusicianCharacterDTO(MusicianCharacter musicianCharacter);
}
