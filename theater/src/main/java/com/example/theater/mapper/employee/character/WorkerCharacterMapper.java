package com.example.theater.mapper.employee.character;

import com.example.theater.dao.entity.employee.character.WorkerCharacter;
import com.example.theater.dto.employee.character.WorkerCharacterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerCharacterMapper {
    WorkerCharacter toWorkerCharacter(WorkerCharacterDTO workerCharacterDTO);

    WorkerCharacterDTO toWorkerCharacterDTO(WorkerCharacter workerCharacter);
}
