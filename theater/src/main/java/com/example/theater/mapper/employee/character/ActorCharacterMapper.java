package com.example.theater.mapper.employee.character;

import com.example.theater.dao.entity.employee.character.ActorCharacter;
import com.example.theater.dto.employee.character.ActorCharacterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActorCharacterMapper {
    @Mapping(target = "id", ignore = true)
    ActorCharacter toNewActorCharacter(ActorCharacterDTO actorCharacterDTO);

    ActorCharacter toActorCharacter(ActorCharacterDTO actorCharacterDTO);

    ActorCharacterDTO toActorCharacterDTO(ActorCharacter actorCharacter);
}
