package com.example.theater.mappers.performances.plays;

import com.example.theater.dao.entities.performances.plays.Genre;
import com.example.theater.dto.performances.plays.GenreDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    Genre toGenre(GenreDTO genreDTO);

    GenreDTO toGenreDTO(Genre genre);
}
