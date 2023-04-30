package com.example.theater.mappers.emploees.categories;

import com.example.theater.dao.entities.emploees.categories.DirectorCategory;
import com.example.theater.dto.emploees.categories.DirectorCategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectorCategoryMapper {
    DirectorCategory toDirectorCategory(DirectorCategoryDTO directorCategoryDTO);

    DirectorCategoryDTO toDirectorCategoryDTO(DirectorCategory directorCategory);
}
