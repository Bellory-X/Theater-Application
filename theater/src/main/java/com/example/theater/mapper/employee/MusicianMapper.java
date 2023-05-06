package com.example.theater.mapper.employee;

import com.example.theater.dao.entity.employee.Musician;
import com.example.theater.dto.employee.MusicianDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicianMapper {
    Musician toMusician(MusicianDTO musicianDTO);

    MusicianDTO toMusicianDTO(Musician musician);
}
