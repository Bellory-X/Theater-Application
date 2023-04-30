package com.example.theater.mappers.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.ActorCharacter;
import com.example.theater.dto.emploees.characters.ActorCharacterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorCharacterMapper {
    ActorCharacter toActorCharacter(ActorCharacterDTO actorCharacterDTO);

    ActorCharacterDTO toActorCharacterDTO(ActorCharacter actorCharacter);
}
