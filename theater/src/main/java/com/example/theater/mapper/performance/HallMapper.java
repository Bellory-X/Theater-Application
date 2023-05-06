package com.example.theater.mapper.performance;

import com.example.theater.dao.entity.performance.Hall;
import com.example.theater.dto.performance.HallDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HallMapper {
    Hall toHall(HallDTO hallDTO);

    HallDTO toHallDTO(Hall hall);
}
