package com.example.theater.mapper.performance.play;

import com.example.theater.dao.entity.performance.play.Author;
import com.example.theater.dto.performance.play.AuthorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "id", ignore = true)
    Author toNewAuthor(AuthorDTO authorDTO);

    Author toAuthor(AuthorDTO authorDTO);

    AuthorDTO toAuthorDTO(Author author);
}
