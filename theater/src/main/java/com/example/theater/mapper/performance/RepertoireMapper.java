package com.example.theater.mapper.performance;

import com.example.theater.dao.entity.performance.Repertoire;
import com.example.theater.dto.performance.RepertoireDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RepertoireMapper {
    Repertoire toRepertoire(RepertoireDTO repertoireDTO);

    RepertoireDTO toRepertoireDTO(Repertoire repertoire);
}
