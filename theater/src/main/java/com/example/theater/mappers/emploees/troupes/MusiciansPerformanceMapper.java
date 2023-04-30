package com.example.theater.mappers.emploees.troupes;

import com.example.theater.dao.entities.emploees.troupes.MusiciansPerformance;
import com.example.theater.dto.emploees.troupes.MusiciansPerformanceDTO;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface MusiciansPerformanceMapper {
    MusiciansPerformance toMusiciansPerformance(MusiciansPerformanceDTO musiciansPerformanceDTO);

    MusiciansPerformanceDTO toMusiciansPerformanceDTO(MusiciansPerformance musiciansPerformance);
}
