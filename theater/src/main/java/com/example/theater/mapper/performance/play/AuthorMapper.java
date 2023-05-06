package com.example.theater.mapper.performance.play;

import com.example.theater.dao.entity.performance.play.Author;
import com.example.theater.dto.performance.play.AuthorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorDTO authorDTO);

    AuthorDTO toAuthorDTO(Author author);
}
