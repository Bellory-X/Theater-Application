package com.example.theater.mappers.emploees.categories;

import com.example.theater.dao.entities.emploees.categories.MusicianCategory;
import com.example.theater.dto.emploees.categories.MusicianCategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicianCategoryMapper {
    MusicianCategory toMusicianCategory(MusicianCategoryDTO musicianCategoryDTO);

    MusicianCategoryDTO toMusicianCategoryDTO(MusicianCategory musicianCategory);
}
