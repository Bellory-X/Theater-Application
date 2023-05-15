package com.example.theater.mapper.performance.play;

import com.example.theater.dao.entity.performance.play.Genre;
import com.example.theater.dto.performance.play.GenreDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    @Mapping(target = "id", ignore = true)
    Genre toNewGenre(GenreDTO genreDTO);

    Genre toGenre(GenreDTO genreDTO);

    GenreDTO toGenreDTO(Genre genre);
}
