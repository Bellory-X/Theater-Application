package com.example.theater.mapper.performance.play;

import com.example.theater.dao.entity.performance.play.Play;
import com.example.theater.dto.performance.play.PlayDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayMapper {
    Play toPlay(PlayDTO playDTO);

    PlayDTO toPlayDTO(Play play);
}
