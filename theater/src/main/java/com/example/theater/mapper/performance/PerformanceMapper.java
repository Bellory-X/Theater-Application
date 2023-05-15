package com.example.theater.mapper.performance;

import com.example.theater.dao.entity.performance.Performance;
import com.example.theater.dto.performance.PerformanceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PlaceMapper.class})
public interface PerformanceMapper {
//    @Mapping(target = "play.id", source = "idPlay")
//    @Mapping(target = "play.name", source = "name")
//    @Mapping(target = "play.data", source = "data")
//    @Mapping(target = "play.rating", source = "rating")
//    @Mapping(target = "play.genre", source = "genre")
    Performance toPerformance(PerformanceDTO performanceDTO);

//    @Mapping(target = "idPlay", source = "play.id")
//    @Mapping(target = "name", source = "play.name")
//    @Mapping(target = "data", source = "play.data")
//    @Mapping(target = "rating", source = "play.rating")
//    @Mapping(target = "genre", source = "play.genre")
    PerformanceDTO toPerformanceDTO(Performance performance);
}
