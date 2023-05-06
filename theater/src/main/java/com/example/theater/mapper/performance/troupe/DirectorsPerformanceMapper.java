package com.example.theater.mapper.performance.troupe;

import com.example.theater.dao.entity.performance.troupe.DirectorsPerformance;
import com.example.theater.dto.performance.troupe.DirectorsPerformanceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectorsPerformanceMapper {
    DirectorsPerformance toDirectorsPerformance(DirectorsPerformanceDTO directorsPerformanceDTO);

    DirectorsPerformanceDTO toDirectorsPerformanceDTO(DirectorsPerformance directorsPerformance);
}
