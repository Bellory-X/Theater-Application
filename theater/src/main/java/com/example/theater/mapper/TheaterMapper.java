package com.example.theater.mapper;

import com.example.theater.dao.entity.Theater;
import com.example.theater.dto.TheaterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TheaterMapper {
    @Mapping(target = "id", ignore = true)
    Theater toNewTheater(TheaterDTO theaterDTO);

    Theater toTheater(TheaterDTO theaterDTO);

    TheaterDTO toTheaterDTO(Theater theater);
}
