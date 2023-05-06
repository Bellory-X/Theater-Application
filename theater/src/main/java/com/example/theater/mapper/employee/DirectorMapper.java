package com.example.theater.mapper.employee;

import com.example.theater.dao.entity.employee.Director;
import com.example.theater.dto.employee.DirectorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    @Mapping(target = "employee", ignore = true)
    Director toDirector(DirectorDTO directorDTO);

    @Mapping(target = "fullName", source = "employee.fullName")
    @Mapping(target = "experience", source = "employee.experience")
    @Mapping(target = "gender", source = "employee.gender")
    @Mapping(target = "birthday", source = "employee.birthday")
    @Mapping(target = "countChild", source = "employee.countChild")
    @Mapping(target = "salary", source = "employee.salary")
    @Mapping(target = "worker", source = "employee.worker")
    @Mapping(target = "theater", source = "employee.theater")
    DirectorDTO toDirectorDTO(Director director);
}
