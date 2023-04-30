package com.example.theater.mappers.performances.plays;

import com.example.theater.dao.entities.performances.plays.Play;
import com.example.theater.dto.performances.plays.PlayDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayMapper {
    Play toPlay(PlayDTO playDTO);

    PlayDTO toPlayDTO(Play play);
}
