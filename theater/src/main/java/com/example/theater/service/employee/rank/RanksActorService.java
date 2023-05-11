package com.example.theater.service.employee.rank;

import com.example.theater.dao.entity.employee.rank.RanksActor;
import com.example.theater.dao.repository.employee.rank.RanksActorRepository;
import com.example.theater.dto.employee.rank.RanksActorDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.rank.RanksActorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RanksActorService {
    RanksActorRepository repository;
    RanksActorMapper mapper;

    public List<RanksActorDTO> getAll() {
        Iterable<RanksActor> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toRanksActorDTO)
                .collect(Collectors.toList());
    }

    public void add(RanksActorDTO ranksActorDTO) {
        RanksActor ranksActor = mapper.toNewRanksActor(ranksActorDTO);
        repository.save(ranksActor);
    }

    public void edit(RanksActorDTO ranksActorDTO) {
        if (!repository.existsById(ranksActorDTO.getId()))
            throw new RecordNotFoundException("Not found " + ranksActorDTO.getId());

        RanksActor ranksActor = mapper.toRanksActor(ranksActorDTO);
        repository.save(ranksActor);
    }

    public void drop(RanksActorDTO ranksActorDTO) {
        if (!repository.existsById(ranksActorDTO.getId()))
            throw new RecordNotFoundException("Not found " + ranksActorDTO.getId());

        RanksActor ranksActor = mapper.toRanksActor(ranksActorDTO);
        repository.delete(ranksActor);
    }
}
