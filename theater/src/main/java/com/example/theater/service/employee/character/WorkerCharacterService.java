package com.example.theater.service.employee.character;

import com.example.theater.dao.entity.employee.character.WorkerCharacter;
import com.example.theater.dao.repository.employee.charecter.WorkerCharacterRepository;
import com.example.theater.dto.employee.character.WorkerCharacterDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.character.WorkerCharacterMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class WorkerCharacterService {
    private final WorkerCharacterRepository repository;
    private final WorkerCharacterMapper mapper;

    public Optional<WorkerCharacterDTO> getById(int id) {
        return repository.findById(id).map(mapper::toWorkerCharacterDTO);
    }

    public List<WorkerCharacterDTO> getAll() {
        Iterable<WorkerCharacter> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toWorkerCharacterDTO)
                .collect(Collectors.toList());
    }

    public void add(WorkerCharacterDTO dto) {
        dto.setId(Generator.generateId());
        WorkerCharacter workerCharacter = mapper.toWorkerCharacter(dto);
        repository.save(workerCharacter);
    }

    public void edit(WorkerCharacterDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getCharacter());

        WorkerCharacter workerCharacter = mapper.toWorkerCharacter(dto);
        repository.save(workerCharacter);
    }

    public void drop(WorkerCharacterDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getCharacter());

        WorkerCharacter workerCharacter = mapper.toWorkerCharacter(dto);
        repository.delete(workerCharacter);
    }
}
