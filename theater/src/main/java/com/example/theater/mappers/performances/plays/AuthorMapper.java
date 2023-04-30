package com.example.theater.mappers.performances.plays;

import com.example.theater.dao.entities.performances.plays.Author;
import com.example.theater.dto.performances.plays.AuthorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorDTO authorDTO);

    AuthorDTO toAuthorDTO(Author author);
}
