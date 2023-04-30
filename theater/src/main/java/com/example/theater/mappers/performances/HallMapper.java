package com.example.theater.mappers.performances;

import com.example.theater.dao.entities.performances.Hall;
import com.example.theater.dto.performances.HallDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HallMapper {
    Hall toHall(HallDTO hallDTO);

    HallDTO toHallDTO(Hall hall);
}
