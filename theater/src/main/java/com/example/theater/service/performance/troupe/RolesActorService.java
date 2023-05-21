package com.example.theater.service.performance.troupe;

import com.example.theater.dao.entity.performance.troupe.RolesActor;
import com.example.theater.dao.repository.performance.troupe.RolesActorRepository;
import com.example.theater.dto.employee.ActorDTO;
import com.example.theater.dto.employee.MusicianDTO;
import com.example.theater.dto.performance.troupe.RolesActorDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.troupe.RolesActorMapper;
import com.example.theater.service.Generator;
import com.example.theater.service.employee.ActorService;
import com.example.theater.service.employee.EmployeeService;
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
    private final RoleService roleService;
    private final ActorService actorService;

    public Optional<RolesActorDTO> getById(int id) {
        return repository.findById(id).map(mapper::toRolesActorDTO);
    }

    public List<ActorDTO> getAllActor(String theater) {
//        TODO: filter
        return actorService.getAll().stream().filter(el -> el.getTheater().equals(theater) || !el.isWorker()).toList();
    }

    public List<RolesActorDTO> getAll() {
        Iterable<RolesActor> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toRolesActorDTO)
                .peek(el -> actorService.getById(el.getIdEmployee()).ifPresent(v -> el.setFullName(v.getFullName())))
                .peek(el -> roleService.getById(el.getIdRole()).ifPresent(v -> {
                    el.setName(v.getName());
                    el.setMain(v.isMain());
                    el.setUnderstudy(v.isUnderstudy());
                    el.setIdPerformance(v.getIdPerformance());
                }))
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
