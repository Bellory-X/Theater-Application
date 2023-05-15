package com.example.theater.service.performance;

import com.example.theater.dao.entity.performance.Repertoire;
import com.example.theater.dao.repository.performance.RepertoireRepository;
import com.example.theater.dto.performance.RepertoireDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.RepertoireMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RepertoireService {
    private final RepertoireRepository repository;
    private final RepertoireMapper mapper;

    public Optional<RepertoireDTO> getById(int id) {
        return repository.findById(id).map(mapper::toRepertoireDTO);
    }

    public List<RepertoireDTO> getAll() {
        Iterable<Repertoire> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toRepertoireDTO)
                .collect(Collectors.toList());
    }

    public void add(RepertoireDTO dto) {
        Repertoire repertoire = mapper.toRepertoire(dto);
        repository.save(repertoire);
    }

    public void edit(RepertoireDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Repertoire repertoire = mapper.toRepertoire(dto);
        repository.save(repertoire);
    }

    public void drop(RepertoireDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Repertoire repertoire = mapper.toRepertoire(dto);
        repository.delete(repertoire);
    }
}
