package com.example.theater.services.emploees.ranks;

import com.example.theater.dao.entities.emploees.ranks.RanksActor;
import com.example.theater.dao.entities.emploees.ranks.RanksActorKey;
import com.example.theater.dao.repositories.emploees.ranks.RanksActorRepository;
import com.example.theater.dto.emploees.ranks.RanksActorDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.ranks.RanksActorMapper;
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
        RanksActor ranksActor = mapper.toRanksActor(ranksActorDTO);
        repository.save(ranksActor);
    }

    public void edit(RanksActorDTO ranksActorDTO) {
        if (!repository.existsById(new RanksActorKey(ranksActorDTO.getId(), ranksActorDTO.getIdRank())))
            throw new RecordNotFoundException("Not found " + ranksActorDTO.getId());

        RanksActor ranksActor = mapper.toRanksActor(ranksActorDTO);
        repository.save(ranksActor);
    }

    public void drop(RanksActorDTO ranksActorDTO) {
        if (!repository.existsById(new RanksActorKey(ranksActorDTO.getId(), ranksActorDTO.getIdRank())))
            throw new RecordNotFoundException("Not found " + ranksActorDTO.getId());

        RanksActor ranksActor = mapper.toRanksActor(ranksActorDTO);
        repository.delete(ranksActor);
    }
}
