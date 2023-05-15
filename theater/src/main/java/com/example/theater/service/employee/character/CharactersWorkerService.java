package com.example.theater.service.employee.character;

import com.example.theater.dao.entity.employee.character.CharactersWorker;
import com.example.theater.dao.repository.employee.charecter.CharactersWorkerRepository;
import com.example.theater.dto.employee.character.CharactersWorkerDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.character.CharactersWorkerMapper;
import com.example.theater.service.Generator;
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

    public void add(CharactersWorkerDTO dto) {
        dto.setId(Generator.generateId());
        CharactersWorker charactersWorker = mapper.toCharactersWorker(dto);
        repository.save(charactersWorker);
    }

    public void edit(CharactersWorkerDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        CharactersWorker charactersWorker = mapper.toCharactersWorker(dto);
        repository.save(charactersWorker);
    }

    public void drop(CharactersWorkerDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        CharactersWorker charactersWorker = mapper.toCharactersWorker(dto);
        repository.delete(charactersWorker);
    }
}
