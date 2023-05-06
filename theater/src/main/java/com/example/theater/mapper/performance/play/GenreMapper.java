package com.example.theater.mapper.performance.play;

import com.example.theater.dao.entity.performance.play.Genre;
import com.example.theater.dto.performance.play.GenreDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    Genre toGenre(GenreDTO genreDTO);

    GenreDTO toGenreDTO(Genre genre);
}
