package com.example.theater.mapper.employee.rank;

import com.example.theater.dao.entity.employee.rank.RanksActor;
import com.example.theater.dto.employee.rank.RanksActorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RanksActorMapper {
    @Mapping(target = "rank", ignore = true)
    RanksActor toRanksActor(RanksActorDTO ranksActorDTO);

    @Mapping(target = "name", source = "rank.name")
    @Mapping(target = "contest", source = "rank.contest")
    @Mapping(target = "data", source = "rank.data")
    RanksActorDTO toRanksActorDTO(RanksActor ranksActor);
}
