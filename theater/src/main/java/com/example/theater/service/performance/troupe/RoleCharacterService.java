package com.example.theater.service.performance.troupe;

import com.example.theater.dao.entity.performance.troupe.RoleCharacter;
import com.example.theater.dao.repository.performance.troupe.RoleCharacterRepository;
import com.example.theater.dto.performance.troupe.RoleCharacterDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.troupe.RoleCharacterMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RoleCharacterService {
    RoleCharacterRepository repository;
    RoleCharacterMapper mapper;

    public List<RoleCharacterDTO> getAll() {
        Iterable<RoleCharacter> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toRoleCharacterDTO)
                .collect(Collectors.toList());
    }

    public void add(RoleCharacterDTO roleCharacterDTO) {
        RoleCharacter roleCharacter = mapper.toRoleCharacter(roleCharacterDTO);
        repository.save(roleCharacter);
    }

    public void edit(RoleCharacterDTO roleCharacterDTO) {
        if (!repository.existsById(roleCharacterDTO.getId()))
            throw new RecordNotFoundException("Not found " + roleCharacterDTO.getId());

        RoleCharacter roleCharacter = mapper.toRoleCharacter(roleCharacterDTO);
        repository.save(roleCharacter);
    }

    public void drop(RoleCharacterDTO roleCharacterDTO) {
        if (!repository.existsById(roleCharacterDTO.getId()))
            throw new RecordNotFoundException("Not found " + roleCharacterDTO.getId());

        RoleCharacter roleCharacter = mapper.toRoleCharacter(roleCharacterDTO);
        repository.delete(roleCharacter);
    }
}
