package com.example.theater.mapper.performance;

import com.example.theater.dao.entity.performance.Place;
import com.example.theater.dto.performance.PlaceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaceMapper {
    Place toPlace(PlaceDTO placeDTO);

    PlaceDTO toPlaceDTO(Place place);
}
