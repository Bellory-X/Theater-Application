package com.example.theater.mapper.performance;

import com.example.theater.dao.entity.performance.TimeHall;
import com.example.theater.dto.performance.TimeHallDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeHallMapper {
    TimeHall toTimeHall(TimeHallDTO timeHallDTO);

    TimeHallDTO toTimeHallDTO(TimeHall repertoire);
}
