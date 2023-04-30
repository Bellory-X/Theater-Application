package com.example.theater.mappers.emploees.troupes;

import com.example.theater.dao.entities.emploees.troupes.Role;
import com.example.theater.dto.emploees.troupes.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleDTO roleDTO);

    RoleDTO toRoleDTO(Role role);
}
