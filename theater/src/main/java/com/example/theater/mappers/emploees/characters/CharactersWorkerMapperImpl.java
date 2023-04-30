package com.example.theater.mappers.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.CharactersWorker;
import com.example.theater.dao.entities.emploees.characters.CharactersWorkerKey;
import com.example.theater.dto.emploees.characters.CharactersWorkerDTO;
import org.springframework.stereotype.Component;

@Component
public class CharactersWorkerMapperImpl implements CharactersWorkerMapper {
    @Override
    public CharactersWorker toCharactersWorker(CharactersWorkerDTO charactersWorkerDTO) {
        if (charactersWorkerDTO ==null) return null;
        return new CharactersWorker(new CharactersWorkerKey(charactersWorkerDTO.getId(), charactersWorkerDTO.getCharacter()));
    }

    @Override
    public CharactersWorkerDTO toCharactersWorkerDTO(CharactersWorker charactersWorker) {
        if (charactersWorker ==null) return null;
        return new CharactersWorkerDTO(charactersWorker.getKey().getId(), charactersWorker.getKey().getCharacter());
    }
}
