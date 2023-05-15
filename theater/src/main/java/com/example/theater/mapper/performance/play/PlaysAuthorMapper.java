package com.example.theater.mapper.performance.play;

import com.example.theater.dao.entity.performance.play.PlaysAuthor;
import com.example.theater.dto.performance.play.PlaysAuthorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlaysAuthorMapper {
    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "author.id", source = "idAuthor")
//    @Mapping(target = "author.fullName", source = "fullName")
//    @Mapping(target = "author.birthday", source = "birthday")
//    @Mapping(target = "author.country", source = "country")
    PlaysAuthor toNewPlaysAuthor(PlaysAuthorDTO playsAuthorDTO);

//    @Mapping(target = "author.id", source = "idAuthor")
//    @Mapping(target = "author.fullName", source = "fullName")
//    @Mapping(target = "author.birthday", source = "birthday")
//    @Mapping(target = "author.country", source = "country")
    PlaysAuthor toPlaysAuthor(PlaysAuthorDTO playsAuthorDTO);

//    @Mapping(target = "idAuthor", source = "author.id")
//    @Mapping(target = "fullName", source = "author.fullName")
//    @Mapping(target = "birthday", source = "author.birthday")
//    @Mapping(target = "country", source = "author.country")
    PlaysAuthorDTO toPlaysAuthorDTO(PlaysAuthor playsAuthor);
}
