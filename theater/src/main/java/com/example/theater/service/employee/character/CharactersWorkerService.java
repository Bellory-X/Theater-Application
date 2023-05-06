package com.example.theater.service.employee.character;

import com.example.theater.dao.entity.employee.character.CharactersWorker;
import com.example.theater.dao.repository.employee.charecter.CharactersWorkerRepository;
import com.example.theater.dto.employee.character.CharactersWorkerDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.character.CharactersWorkerMapper;
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
        if (!repository.existsById(charactersWorkerDTO.getId()))
            throw new RecordNotFoundException("Not found " + charactersWorkerDTO.getId());

        CharactersWorker charactersWorker = mapper.toCharactersWorker(charactersWorkerDTO);
        repository.save(charactersWorker);
    }

    public void drop(CharactersWorkerDTO charactersWorkerDTO) {
        if (!repository.existsById(charactersWorkerDTO.getId()))
            throw new RecordNotFoundException("Not found " + charactersWorkerDTO.getId());

        CharactersWorker charactersWorker = mapper.toCharactersWorker(charactersWorkerDTO);
        repository.delete(charactersWorker);
    }
}
