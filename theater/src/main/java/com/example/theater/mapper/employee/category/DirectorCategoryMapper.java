package com.example.theater.mapper.employee.category;

import com.example.theater.dao.entity.employee.category.DirectorCategory;
import com.example.theater.dto.employee.category.DirectorCategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectorCategoryMapper {
    DirectorCategory toDirectorCategory(DirectorCategoryDTO directorCategoryDTO);

    DirectorCategoryDTO toDirectorCategoryDTO(DirectorCategory directorCategory);
}
