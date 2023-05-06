package com.example.theater.mapper.employee.character;

import com.example.theater.dao.entity.employee.character.CharactersActor;
import com.example.theater.dto.employee.character.CharactersActorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharactersActorMapper {
    CharactersActor toCharactersActor(CharactersActorDTO charactersActorDTO);

    CharactersActorDTO toCharactersActorDTO(CharactersActor charactersActor);
}
