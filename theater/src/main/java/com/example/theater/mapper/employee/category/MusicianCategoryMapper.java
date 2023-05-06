package com.example.theater.mapper.employee.category;

import com.example.theater.dao.entity.employee.category.MusicianCategory;
import com.example.theater.dto.employee.category.MusicianCategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicianCategoryMapper {
    MusicianCategory toMusicianCategory(MusicianCategoryDTO musicianCategoryDTO);

    MusicianCategoryDTO toMusicianCategoryDTO(MusicianCategory musicianCategory);
}
