package com.example.theater.mappers.emploees.troupes;

import com.example.theater.dao.entities.emploees.troupes.MusiciansPerformance;
import com.example.theater.dao.entities.emploees.troupes.MusiciansPerformanceKey;
import com.example.theater.dto.emploees.troupes.MusiciansPerformanceDTO;
import org.springframework.stereotype.Component;

@Component
public class MusiciansPerformanceMapperImpl implements MusiciansPerformanceMapper {
    @Override
    public MusiciansPerformance toMusiciansPerformance(MusiciansPerformanceDTO musiciansPerformanceDTO) {
        if (musiciansPerformanceDTO == null) return null;
        return new MusiciansPerformance(new MusiciansPerformanceKey(musiciansPerformanceDTO.getIdPerformance(), musiciansPerformanceDTO.getIdEmployee()));
    }

    @Override
    public MusiciansPerformanceDTO toMusiciansPerformanceDTO(MusiciansPerformance musiciansPerformance) {
        if (musiciansPerformance == null) return null;
        return new MusiciansPerformanceDTO(musiciansPerformance.getKey().getIdPerformance(), musiciansPerformance.getKey().getIdEmployee());
    }
}
