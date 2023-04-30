package com.example.theater.mappers.emploees.troupes;

import com.example.theater.dao.entities.emploees.troupes.DirectorsPerformance;
import com.example.theater.dao.entities.emploees.troupes.DirectorsPerformanceKey;
import com.example.theater.dto.emploees.troupes.DirectorsPerformanceDTO;
import org.springframework.stereotype.Component;

@Component
public class DirectorsPerformanceMapperImpl implements DirectorsPerformanceMapper {
    @Override
    public DirectorsPerformance toDirectorsPerformance(DirectorsPerformanceDTO directorsPerformanceDTO) {
        if (directorsPerformanceDTO == null) return null;
        return new DirectorsPerformance(new DirectorsPerformanceKey(directorsPerformanceDTO.getIdPerformance(), directorsPerformanceDTO.getIdEmployee()));
    }

    @Override
    public DirectorsPerformanceDTO toDirectorsPerformanceDTO(DirectorsPerformance directorsPerformance) {
        if (directorsPerformance == null) return null;
        return new DirectorsPerformanceDTO(directorsPerformance.getKey().getIdPerformance(), directorsPerformance.getKey().getIdEmployee());
    }
}
