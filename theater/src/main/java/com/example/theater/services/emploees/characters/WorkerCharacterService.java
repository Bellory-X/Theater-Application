package com.example.theater.services.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.WorkerCharacter;
import com.example.theater.dao.repositories.emploees.charecters.WorkerCharacterRepository;
import com.example.theater.dto.emploees.characters.WorkerCharacterDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.characters.WorkerCharacterMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class WorkerCharacterService {
    private final WorkerCharacterRepository repository;
    private final WorkerCharacterMapper mapper;

    public List<WorkerCharacterDTO> getAll() {
        Iterable<WorkerCharacter> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toWorkerCharacterDTO)
                .collect(Collectors.toList());
    }

    public void add(WorkerCharacterDTO workerCharacterDTO) {
        WorkerCharacter workerCharacter = mapper.toWorkerCharacter(workerCharacterDTO);
        repository.save(workerCharacter);
    }

    public void edit(WorkerCharacterDTO workerCharacterDTO) {
        if (!repository.existsById(workerCharacterDTO.getCharacter()))
            throw new RecordNotFoundException("Not found " + workerCharacterDTO.getCharacter());

        WorkerCharacter workerCharacter = mapper.toWorkerCharacter(workerCharacterDTO);
        repository.save(workerCharacter);
    }

    public void drop(WorkerCharacterDTO workerCharacterDTO) {
        if (!repository.existsById(workerCharacterDTO.getCharacter()))
            throw new RecordNotFoundException("Not found " + workerCharacterDTO.getCharacter());

        WorkerCharacter workerCharacter = mapper.toWorkerCharacter(workerCharacterDTO);
        repository.delete(workerCharacter);
    }
}
