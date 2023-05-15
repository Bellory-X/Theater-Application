package com.example.theater.service.performance.troupe;

import com.example.theater.dao.entity.performance.troupe.Role;
import com.example.theater.dao.repository.performance.troupe.RoleRepository;
import com.example.theater.dto.performance.troupe.RoleDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.troupe.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository repository;
    private final RoleMapper mapper;

    public Optional<RoleDTO> getById(int id) {
        return repository.findById(id).map(mapper::toRoleDTO);
    }

    public List<RoleDTO> getAll() {
        Iterable<Role> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toRoleDTO)
                .collect(Collectors.toList());
    }

    public void add(RoleDTO dto) {
        Role role = mapper.toRole(dto);
        repository.save(role);
    }

    public void edit(RoleDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Role role = mapper.toRole(dto);
        repository.save(role);
    }

    public void drop(RoleDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Role role = mapper.toRole(dto);
        repository.delete(role);
    }
}
