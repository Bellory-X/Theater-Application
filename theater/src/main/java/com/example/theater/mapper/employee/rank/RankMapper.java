package com.example.theater.mapper.employee.rank;

import com.example.theater.dao.entity.employee.rank.Rank;
import com.example.theater.dto.employee.rank.RankDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RankMapper {
    @Mapping(target = "id", ignore = true)
    Rank toNewRank(RankDTO rankDTO);

    Rank toRank(RankDTO rankDTO);

    RankDTO toRankDTO(Rank rank);
}
