package com.example.theater.mapper.employee.category;

import com.example.theater.dao.entity.employee.category.MusicianCategory;
import com.example.theater.dto.employee.category.MusicianCategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MusicianCategoryMapper {
    @Mapping(target = "id", ignore = true)
    MusicianCategory toNewMusicianCategory(MusicianCategoryDTO musicianCategoryDTO);

    MusicianCategory toMusicianCategory(MusicianCategoryDTO musicianCategoryDTO);

    MusicianCategoryDTO toMusicianCategoryDTO(MusicianCategory musicianCategory);
}
