package com.example.theater.mappers.emploees;

import com.example.theater.dao.entities.emploees.Musician;
import com.example.theater.dto.emploees.MusicianDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicianMapper {
    Musician toMusician(MusicianDTO musicianDTO);

    MusicianDTO toMusicianDTO(Musician musician);
}
