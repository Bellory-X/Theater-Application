package com.example.theater.mappers;

import com.example.theater.dao.entities.Theater;
import com.example.theater.dto.TheaterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TheaterMapper {
    Theater toTheater(TheaterDTO theaterDTO);

    TheaterDTO toTheaterDTO(Theater theater);
}
