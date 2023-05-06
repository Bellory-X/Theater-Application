package com.example.theater.mapper.performance.troupe;

import com.example.theater.dao.entity.performance.troupe.Role;
import com.example.theater.dto.performance.troupe.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleDTO roleDTO);

    RoleDTO toRoleDTO(Role role);
}
