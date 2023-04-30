package com.example.theater.services.emploees.characters;

import com.example.theater.dao.entities.emploees.characters.RoleCharacter;
import com.example.theater.dao.entities.emploees.characters.RoleCharacterKey;
import com.example.theater.dao.repositories.emploees.charecters.RoleCharacterRepository;
import com.example.theater.dto.emploees.characters.RoleCharacterDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.characters.RoleCharacterMapper;
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
        if (!repository.existsById(new RoleCharacterKey(roleCharacterDTO.getId(), roleCharacterDTO.getCharacter())))
            throw new RecordNotFoundException("Not found " + roleCharacterDTO.getId());

        RoleCharacter roleCharacter = mapper.toRoleCharacter(roleCharacterDTO);
        repository.save(roleCharacter);
    }

    public void drop(RoleCharacterDTO roleCharacterDTO) {
        if (!repository.existsById(new RoleCharacterKey(roleCharacterDTO.getId(), roleCharacterDTO.getCharacter())))
            throw new RecordNotFoundException("Not found " + roleCharacterDTO.getId());

        RoleCharacter roleCharacter = mapper.toRoleCharacter(roleCharacterDTO);
        repository.delete(roleCharacter);
    }
}
