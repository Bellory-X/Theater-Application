package com.example.theater.mapper.performance.troupe;

import com.example.theater.dao.entity.performance.troupe.MusiciansPerformance;
import com.example.theater.dto.performance.troupe.MusiciansPerformanceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusiciansPerformanceMapper {
    MusiciansPerformance toMusiciansPerformance(MusiciansPerformanceDTO musiciansPerformanceDTO);

    MusiciansPerformanceDTO toMusiciansPerformanceDTO(MusiciansPerformance musiciansPerformance);
}
