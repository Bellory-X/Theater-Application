package com.example.theater.mapper.employee;

import com.example.theater.dao.entity.employee.Director;
import com.example.theater.dto.employee.DirectorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    Director toDirector(DirectorDTO directorDTO);

    DirectorDTO toDirectorDTO(Director director);
}
