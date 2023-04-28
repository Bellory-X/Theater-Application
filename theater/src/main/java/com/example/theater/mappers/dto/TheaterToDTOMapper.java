package com.example.theater.mappers.dto;

import com.example.theater.dao.requests.TheaterRequest;
import com.example.theater.dto.TheaterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TheaterToDTOMapper {
    TheaterDTO AddTheaterRequestToTheater(TheaterRequest theaterRequest);
    TheaterDTO EditBookRequestToBook(String id, TheaterRequest theaterRequest);
}
