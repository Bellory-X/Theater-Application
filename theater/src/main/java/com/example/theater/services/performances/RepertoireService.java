package com.example.theater.services.performances;

import com.example.theater.dao.entities.performances.Repertoire;
import com.example.theater.dao.repositories.performances.RepertoireRepository;
import com.example.theater.dto.performances.RepertoireDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.performances.RepertoireMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RepertoireService {
    private final RepertoireRepository repository;
    private final RepertoireMapper mapper;

    public List<RepertoireDTO> getAll() {
        Iterable<Repertoire> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toRepertoireDTO)
                .collect(Collectors.toList());
    }

    public void add(RepertoireDTO repertoireDTO) {
        Repertoire repertoire = mapper.toRepertoire(repertoireDTO);
        repository.save(repertoire);
    }

    public void edit(RepertoireDTO repertoireDTO) {
        if (!repository.existsById(repertoireDTO.getId()))
            throw new RecordNotFoundException("Not found " + repertoireDTO.getId());

        Repertoire repertoire = mapper.toRepertoire(repertoireDTO);
        repository.save(repertoire);
    }

    public void drop(RepertoireDTO repertoireDTO) {
        if (!repository.existsById(repertoireDTO.getId()))
            throw new RecordNotFoundException("Not found " + repertoireDTO.getId());

        Repertoire repertoire = mapper.toRepertoire(repertoireDTO);
        repository.delete(repertoire);
    }
}
