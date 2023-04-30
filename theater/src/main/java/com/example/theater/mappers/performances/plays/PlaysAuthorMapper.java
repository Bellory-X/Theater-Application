package com.example.theater.mappers.performances.plays;

import com.example.theater.dao.entities.performances.plays.PlaysAuthor;
import com.example.theater.dto.performances.plays.PlaysAuthorDTO;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface PlaysAuthorMapper {
    PlaysAuthor toPlaysAuthor(PlaysAuthorDTO playsAuthorDTO);

    PlaysAuthorDTO toPlaysAuthorDTO(PlaysAuthor playsAuthor);
}
