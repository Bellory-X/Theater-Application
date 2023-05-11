package com.example.theater.mapper.employee.character;

import com.example.theater.dao.entity.employee.character.WorkerCharacter;
import com.example.theater.dto.employee.character.WorkerCharacterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkerCharacterMapper {
    @Mapping(target = "id", ignore = true)
    WorkerCharacter toNewWorkerCharacter(WorkerCharacterDTO workerCharacterDTO);

    WorkerCharacter toWorkerCharacter(WorkerCharacterDTO workerCharacterDTO);

    WorkerCharacterDTO toWorkerCharacterDTO(WorkerCharacter workerCharacter);
}
