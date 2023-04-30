package com.example.theater.services.emploees.troupes;

import com.example.theater.dao.entities.emploees.troupes.Role;
import com.example.theater.dao.repositories.emploees.troupes.RoleRepository;
import com.example.theater.dto.emploees.troupes.RoleDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.troupes.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository repository;
    private final RoleMapper mapper;

    public List<RoleDTO> getAll() {
        Iterable<Role> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toRoleDTO)
                .collect(Collectors.toList());
    }

    public void add(RoleDTO roleDTO) {
        Role role = mapper.toRole(roleDTO);
        repository.save(role);
    }

    public void edit(RoleDTO roleDTO) {
        if (!repository.existsById(roleDTO.getId()))
            throw new RecordNotFoundException("Not found " + roleDTO.getId());

        Role role = mapper.toRole(roleDTO);
        repository.save(role);
    }

    public void drop(RoleDTO roleDTO) {
        if (!repository.existsById(roleDTO.getId()))
            throw new RecordNotFoundException("Not found " + roleDTO.getId());

        Role role = mapper.toRole(roleDTO);
        repository.delete(role);
    }
}
