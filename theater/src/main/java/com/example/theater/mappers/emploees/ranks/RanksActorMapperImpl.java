package com.example.theater.mappers.emploees.ranks;

import com.example.theater.dao.entities.emploees.ranks.RanksActor;
import com.example.theater.dao.entities.emploees.ranks.RanksActorKey;
import com.example.theater.dto.emploees.ranks.RanksActorDTO;
import org.springframework.stereotype.Component;

@Component
public class RanksActorMapperImpl implements RanksActorMapper {
    @Override
    public RanksActor toRanksActor(RanksActorDTO ranksActorDTO) {
        if (ranksActorDTO == null) return null;
        return new RanksActor(new RanksActorKey(ranksActorDTO.getId(), ranksActorDTO.getIdRank()));
    }

    @Override
    public RanksActorDTO toRanksActorDTO(RanksActor ranksActor) {
        if (ranksActor == null) return null;
        return new RanksActorDTO(ranksActor.getKey().getId(), ranksActor.getKey().getIdRank());
    }
}
