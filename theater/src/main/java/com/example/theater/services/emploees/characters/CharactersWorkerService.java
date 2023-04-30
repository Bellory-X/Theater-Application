package com.example.theater.services.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.CharactersWorker;
import com.example.theater.dao.entities.emploees.characters.CharactersWorkerKey;
import com.example.theater.dao.repositories.emploees.charecters.CharactersWorkerRepository;
import com.example.theater.dto.emploees.characters.CharactersWorkerDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.characters.CharactersWorkerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CharactersWorkerService {
    CharactersWorkerRepository repository;
    CharactersWorkerMapper mapper;

    public List<CharactersWorkerDTO> getAll() {
        Iterable<CharactersWorker> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toCharactersWorkerDTO)
                .collect(Collectors.toList());
    }

    public void add(CharactersWorkerDTO charactersWorkerDTO) {
        CharactersWorker charactersWorker = mapper.toCharactersWorker(charactersWorkerDTO);
        repository.save(charactersWorker);
    }

    public void edit(CharactersWorkerDTO charactersWorkerDTO) {
        if (!repository.existsById(new CharactersWorkerKey(charactersWorkerDTO.getId(), charactersWorkerDTO.getCharacter())))
            throw new RecordNotFoundException("Not found " + charactersWorkerDTO.getId());

        CharactersWorker charactersWorker = mapper.toCharactersWorker(charactersWorkerDTO);
        repository.save(charactersWorker);
    }

    public void drop(CharactersWorkerDTO charactersWorkerDTO) {
        if (!repository.existsById(new CharactersWorkerKey(charactersWorkerDTO.getId(), charactersWorkerDTO.getCharacter())))
            throw new RecordNotFoundException("Not found " + charactersWorkerDTO.getId());

        CharactersWorker charactersWorker = mapper.toCharactersWorker(charactersWorkerDTO);
        repository.delete(charactersWorker);
    }
}
