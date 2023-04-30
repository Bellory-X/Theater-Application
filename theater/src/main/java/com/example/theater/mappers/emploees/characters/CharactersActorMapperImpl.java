package com.example.theater.mappers.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.CharactersActor;
import com.example.theater.dao.entities.emploees.characters.CharactersActorKey;
import com.example.theater.dto.emploees.characters.CharactersActorDTO;
import org.springframework.stereotype.Component;

@Component
public class CharactersActorMapperImpl implements CharactersActorMapper {
    @Override
    public CharactersActor toCharactersActor(CharactersActorDTO charactersActorDTO) {
        if (charactersActorDTO == null) return null;
        return new CharactersActor(new CharactersActorKey(charactersActorDTO.getId(), charactersActorDTO.getCharacter()));
    }

    @Override
    public CharactersActorDTO toCharactersActorDTO(CharactersActor charactersActor) {
        if (charactersActor == null) return null;
        return new CharactersActorDTO(charactersActor.getKey().getId(), charactersActor.getKey().getCharacter());
    }
}
