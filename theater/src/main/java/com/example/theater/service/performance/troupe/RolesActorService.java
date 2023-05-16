package com.example.theater.service.performance.troupe;

import com.example.theater.dao.entity.performance.troupe.RolesActor;
import com.example.theater.dao.repository.performance.troupe.RolesActorRepository;
import com.example.theater.dto.performance.troupe.RolesActorDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.troupe.RolesActorMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RolesActorService {
    private final RolesActorRepository repository;
    private final RolesActorMapper mapper;

    public Optional<RolesActorDTO> getById(int id) {
        return repository.findById(id).map(mapper::toRolesActorDTO);
    }

    public List<RolesActorDTO> getAll() {
        Iterable<RolesActor> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toRolesActorDTO)
                .collect(Collectors.toList());
    }

    public void add(RolesActorDTO dto) {
        dto.setId(Generator.generateId());
        RolesActor rolesActor = mapper.toRolesActor(dto);
        repository.save(rolesActor);
    }

    public void edit(RolesActorDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        RolesActor rolesActor = mapper.toRolesActor(dto);
        repository.save(rolesActor);
    }

    public void drop(RolesActorDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        RolesActor rolesActor = mapper.toRolesActor(dto);
        repository.delete(rolesActor);
    }
}
