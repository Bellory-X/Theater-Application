package com.example.theater.mappers.performances;

import com.example.theater.dao.entities.performances.Repertoire;
import com.example.theater.dto.performances.RepertoireDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RepertoireMapper {
    Repertoire toRepertoire(RepertoireDTO repertoireDTO);

    RepertoireDTO toRepertoireDTO(Repertoire repertoire);
}
