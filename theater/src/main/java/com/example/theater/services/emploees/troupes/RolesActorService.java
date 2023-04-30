package com.example.theater.services.emploees.troupes;

import com.example.theater.dao.entities.emploees.troupes.RolesActor;
import com.example.theater.dao.entities.emploees.troupes.RolesActorKey;
import com.example.theater.dao.repositories.emploees.troupes.RolesActorRepository;
import com.example.theater.dto.emploees.troupes.RolesActorDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.troupes.RolesActorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RolesActorService {
    private final RolesActorRepository repository;
    private final RolesActorMapper mapper;

    public List<RolesActorDTO> getAll() {
        Iterable<RolesActor> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toRolesActorDTO)
                .collect(Collectors.toList());
    }

    public void add(RolesActorDTO rolesActorDTO) {
        RolesActor rolesActor = mapper.toRolesActor(rolesActorDTO);
        repository.save(rolesActor);
    }

    public void edit(RolesActorDTO rolesActorDTO) {
        if (!repository.existsById(new RolesActorKey(rolesActorDTO.getId(), rolesActorDTO.getIdEmployee())))
            throw new RecordNotFoundException("Not found " + rolesActorDTO.getId());

        RolesActor rolesActor = mapper.toRolesActor(rolesActorDTO);
        repository.save(rolesActor);
    }

    public void drop(RolesActorDTO rolesActorDTO) {
        if (!repository.existsById(new RolesActorKey(rolesActorDTO.getId(), rolesActorDTO.getIdEmployee())))
            throw new RecordNotFoundException("Not found " + rolesActorDTO.getId());

        RolesActor rolesActor = mapper.toRolesActor(rolesActorDTO);
        repository.delete(rolesActor);
    }
}
