package com.example.theater.mapper.employee.rank;

import com.example.theater.dao.entity.employee.rank.RanksActor;
import com.example.theater.dto.employee.rank.RanksActorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RanksActorMapper {
    RanksActor toRanksActor(RanksActorDTO ranksActorDTO);

    RanksActorDTO toRanksActorDTO(RanksActor ranksActor);
}
