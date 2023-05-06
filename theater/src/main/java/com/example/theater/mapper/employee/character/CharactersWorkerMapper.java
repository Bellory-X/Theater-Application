package com.example.theater.mapper.employee.character;

import com.example.theater.dao.entity.employee.character.CharactersWorker;
import com.example.theater.dto.employee.character.CharactersWorkerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharactersWorkerMapper {
    CharactersWorker toCharactersWorker(CharactersWorkerDTO charactersWorkerDTO);

    CharactersWorkerDTO toCharactersWorkerDTO(CharactersWorker charactersWorker);
}
