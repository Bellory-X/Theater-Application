package com.example.theater.service.performance.troupe;

import com.example.theater.dao.entity.performance.troupe.RoleCharacter;
import com.example.theater.dao.repository.performance.troupe.RoleCharacterRepository;
import com.example.theater.dto.performance.troupe.RoleCharacterDTO;
import com.example.theater.exception.QueryException;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.troupe.RoleCharacterMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RoleCharacterService {
    RoleCharacterRepository repository;
    RoleCharacterMapper mapper;

    public Optional<RoleCharacterDTO> getById(int id) {
        return repository.findById(id).map(mapper::toRoleCharacterDTO);
    }

    public List<RoleCharacterDTO> getAll() {
        Iterable<RoleCharacter> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toRoleCharacterDTO)
                .collect(Collectors.toList());
    }

    public void add(RoleCharacterDTO dto) {
        try {
            dto.setId(Generator.generateId());
            RoleCharacter roleCharacter = mapper.toRoleCharacter(dto);
            repository.save(roleCharacter);
        } catch (DataAccessException e) {
            throw new QueryException("Recheck fields maybe it exists");
        }
    }

    public void edit(RoleCharacterDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        RoleCharacter roleCharacter = mapper.toRoleCharacter(dto);
        repository.save(roleCharacter);
    }

    public void drop(RoleCharacterDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        RoleCharacter roleCharacter = mapper.toRoleCharacter(dto);
        repository.delete(roleCharacter);
    }
}
