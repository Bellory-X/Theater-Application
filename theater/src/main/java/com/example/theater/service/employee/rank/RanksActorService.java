package com.example.theater.service.employee.rank;

import com.example.theater.dao.entity.employee.rank.RanksActor;
import com.example.theater.dao.repository.employee.rank.RanksActorRepository;
import com.example.theater.dto.employee.rank.RankDTO;
import com.example.theater.dto.employee.rank.RanksActorDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.rank.RanksActorMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RanksActorService {
    RanksActorRepository repository;
    RanksActorMapper mapper;

    public Optional<RanksActorDTO> getById(int id) {
        return repository.findById(id).map(mapper::toRanksActorDTO);
    }

    public List<RanksActorDTO> getAll() {
        Iterable<RanksActor> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toRanksActorDTO)
                .collect(Collectors.toList());
    }

    public void add(RanksActorDTO dto) {
        dto.setId(Generator.generateId());
        RanksActor ranksActor = mapper.toRanksActor(dto);
        repository.save(ranksActor);
    }

    public void edit(RanksActorDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        RanksActor ranksActor = mapper.toRanksActor(dto);
        repository.save(ranksActor);
    }

    public void drop(RanksActorDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        RanksActor ranksActor = mapper.toRanksActor(dto);
        repository.delete(ranksActor);
    }
}
