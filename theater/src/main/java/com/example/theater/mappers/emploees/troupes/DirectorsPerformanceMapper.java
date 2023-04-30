package com.example.theater.mappers.emploees.troupes;

import com.example.theater.dao.entities.emploees.troupes.DirectorsPerformance;
import com.example.theater.dto.emploees.troupes.DirectorsPerformanceDTO;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface DirectorsPerformanceMapper {
    DirectorsPerformance toDirectorsPerformance(DirectorsPerformanceDTO directorsPerformanceDTO);

    DirectorsPerformanceDTO toDirectorsPerformanceDTO(DirectorsPerformance directorsPerformance);
}
