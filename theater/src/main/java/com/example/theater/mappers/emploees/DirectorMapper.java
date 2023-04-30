package com.example.theater.mappers.emploees;

import com.example.theater.dao.entities.emploees.Director;
import com.example.theater.dto.emploees.DirectorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    Director toDirector(DirectorDTO directorDTO);

    DirectorDTO toDirectorDTO(Director director);
}
