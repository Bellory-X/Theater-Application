package com.example.theater.mappers.performances;

import com.example.theater.dao.entities.performances.Performance;
import com.example.theater.dto.performances.PerformanceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PerformanceMapper {
    Performance toPerformance(PerformanceDTO performanceDTO);

    PerformanceDTO toPerformanceDTO(Performance performance);
}
