package com.example.theater.mapper.performance.play;

import com.example.theater.dao.entity.performance.play.PlaysAuthor;
import com.example.theater.dto.performance.play.PlaysAuthorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaysAuthorMapper {
    PlaysAuthor toPlaysAuthor(PlaysAuthorDTO playsAuthorDTO);

    PlaysAuthorDTO toPlaysAuthorDTO(PlaysAuthor playsAuthor);
}
