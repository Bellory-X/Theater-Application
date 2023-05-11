package com.example.theater.mapper.employee.rank;

import com.example.theater.dao.entity.employee.rank.RanksActor;
import com.example.theater.dto.employee.rank.RanksActorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RanksActorMapper {
    @Mapping(target = "id", ignore = true)
    RanksActor toNewRanksActor(RanksActorDTO ranksActorDTO);

    RanksActor toRanksActor(RanksActorDTO ranksActorDTO);

    RanksActorDTO toRanksActorDTO(RanksActor ranksActor);
}
