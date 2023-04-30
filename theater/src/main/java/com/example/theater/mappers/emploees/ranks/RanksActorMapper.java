package com.example.theater.mappers.emploees.ranks;

import com.example.theater.dao.entities.emploees.ranks.RanksActor;
import com.example.theater.dto.emploees.ranks.RanksActorDTO;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface RanksActorMapper {
    RanksActor toRanksActor(RanksActorDTO ranksActorDTO);

    RanksActorDTO toRanksActorDTO(RanksActor ranksActor);
}
