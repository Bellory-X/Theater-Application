package com.example.theater.mapper.employee;

import com.example.theater.dao.entity.employee.Musician;
import com.example.theater.dto.employee.MusicianDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MusicianMapper {
    @Mapping(target = "employee", ignore = true)
    Musician toMusician(MusicianDTO musicianDTO);

    @Mapping(target = "fullName", source = "employee.fullName")
    @Mapping(target = "experience", source = "employee.experience")
    @Mapping(target = "gender", source = "employee.gender")
    @Mapping(target = "birthday", source = "employee.birthday")
    @Mapping(target = "countChild", source = "employee.countChild")
    @Mapping(target = "salary", source = "employee.salary")
    @Mapping(target = "worker", source = "employee.worker")
    @Mapping(target = "theater", source = "employee.theater")
    MusicianDTO toMusicianDTO(Musician musician);
}
