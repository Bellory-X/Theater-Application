package com.example.theater.mappers.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.CharactersActor;
import com.example.theater.dto.emploees.characters.CharactersActorDTO;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface CharactersActorMapper {
    CharactersActor toCharactersActor(CharactersActorDTO charactersActorDTO);

    CharactersActorDTO toCharactersActorDTO(CharactersActor charactersActor);
}
