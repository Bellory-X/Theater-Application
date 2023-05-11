package com.example.theater.mapper.employee.character;

import com.example.theater.dao.entity.employee.character.CharactersWorker;
import com.example.theater.dto.employee.character.CharactersWorkerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharactersWorkerMapper {
    @Mapping(target = "id", ignore = true)
    CharactersWorker toNewCharactersWorker(CharactersWorkerDTO charactersWorkerDTO);

    CharactersWorker toCharactersWorker(CharactersWorkerDTO charactersWorkerDTO);

    CharactersWorkerDTO toCharactersWorkerDTO(CharactersWorker charactersWorker);
}
