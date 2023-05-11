package com.example.theater.mapper.employee.character;

import com.example.theater.dao.entity.employee.character.CharactersActor;
import com.example.theater.dto.employee.character.CharactersActorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharactersActorMapper {
    @Mapping(target = "id", ignore = true)
    CharactersActor toNewCharactersActor(CharactersActorDTO charactersActorDTO);

    CharactersActor toCharactersActor(CharactersActorDTO charactersActorDTO);

    CharactersActorDTO toCharactersActorDTO(CharactersActor charactersActor);
}
