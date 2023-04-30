package com.example.theater.mappers.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.WorkerCharacter;
import com.example.theater.dto.emploees.characters.WorkerCharacterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerCharacterMapper {
    WorkerCharacter toWorkerCharacter(WorkerCharacterDTO workerCharacterDTO);

    WorkerCharacterDTO toWorkerCharacterDTO(WorkerCharacter workerCharacter);
}
