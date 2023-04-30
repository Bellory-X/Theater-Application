package com.example.theater.mappers.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.CharactersWorker;
import com.example.theater.dto.emploees.characters.CharactersWorkerDTO;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface CharactersWorkerMapper {
    CharactersWorker toCharactersWorker(CharactersWorkerDTO charactersWorkerDTO);

    CharactersWorkerDTO toCharactersWorkerDTO(CharactersWorker charactersWorker);
}
