package com.example.theater.mapper.performance;

import com.example.theater.dao.entity.performance.Performance;
import com.example.theater.dto.performance.PerformanceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PerformanceMapper {
    Performance toPerformance(PerformanceDTO performanceDTO);

    PerformanceDTO toPerformanceDTO(Performance performance);
}
