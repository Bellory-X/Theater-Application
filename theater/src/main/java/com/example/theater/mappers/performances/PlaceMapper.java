package com.example.theater.mappers.performances;

import com.example.theater.dao.entities.performances.Place;
import com.example.theater.dto.performances.PlaceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaceMapper {
    Place toPlace(PlaceDTO placeDTO);

    PlaceDTO toPlaceDTO(Place place);
}
