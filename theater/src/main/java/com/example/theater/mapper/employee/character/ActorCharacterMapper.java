package com.example.theater.mapper.employee.character;

import com.example.theater.dao.entity.employee.character.ActorCharacter;
import com.example.theater.dto.employee.character.ActorCharacterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorCharacterMapper {
    ActorCharacter toActorCharacter(ActorCharacterDTO actorCharacterDTO);

    ActorCharacterDTO toActorCharacterDTO(ActorCharacter actorCharacter);
}
