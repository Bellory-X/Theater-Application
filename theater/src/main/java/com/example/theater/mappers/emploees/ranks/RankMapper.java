package com.example.theater.mappers.emploees.ranks;

import com.example.theater.dao.entities.emploees.ranks.Rank;
import com.example.theater.dto.emploees.ranks.RankDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RankMapper {
    Rank toRank(RankDTO rankDTO);

    RankDTO toRankDTO(Rank rank);
}
