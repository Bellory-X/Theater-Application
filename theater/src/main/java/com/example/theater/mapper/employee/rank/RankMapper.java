package com.example.theater.mapper.employee.rank;

import com.example.theater.dao.entity.employee.rank.Rank;
import com.example.theater.dto.employee.rank.RankDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RankMapper {
    Rank toRank(RankDTO rankDTO);

    RankDTO toRankDTO(Rank rank);
}
