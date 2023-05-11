package com.example.theater.mapper.employee.character;

import com.example.theater.dao.entity.employee.character.MusicianCharacter;
import com.example.theater.dto.employee.character.MusicianCharacterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MusicianCharacterMapper {
    @Mapping(target = "id", ignore = true)
    MusicianCharacter toNewMusicianCharacter(MusicianCharacterDTO musicianCharacterDTO);

    MusicianCharacter toMusicianCharacter(MusicianCharacterDTO musicianCharacterDTO);

    MusicianCharacterDTO toMusicianCharacterDTO(MusicianCharacter musicianCharacter);
}
