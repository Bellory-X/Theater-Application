package com.example.theater.mappers.entities;

import com.example.theater.dao.entities.Theater;
import com.example.theater.dto.TheaterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TheaterToEntityMapper {
    Theater theaterToTheaterEntity(TheaterDTO theater);

    TheaterDTO theaterEntityToTheater(Theater theater);
}
