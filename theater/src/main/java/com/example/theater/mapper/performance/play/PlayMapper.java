package com.example.theater.mapper.performance.play;

import com.example.theater.dao.entity.performance.play.Play;
import com.example.theater.dto.performance.play.PlayDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayMapper {
    @Mapping(target = "id", ignore = true)
    Play toNewPlay(PlayDTO playDTO);

    Play toPlay(PlayDTO playDTO);

    PlayDTO toPlayDTO(Play play);
}
