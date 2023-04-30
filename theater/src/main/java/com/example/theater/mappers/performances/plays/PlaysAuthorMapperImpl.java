package com.example.theater.mappers.performances.plays;

import com.example.theater.dao.entities.performances.plays.PlaysAuthor;
import com.example.theater.dao.entities.performances.plays.PlaysAuthorKey;
import com.example.theater.dto.performances.plays.PlaysAuthorDTO;
import org.springframework.stereotype.Component;

@Component
public class PlaysAuthorMapperImpl implements PlaysAuthorMapper {
    @Override
    public PlaysAuthor toPlaysAuthor(PlaysAuthorDTO playsAuthorDTO) {
        if ( playsAuthorDTO == null ) {
            return null;
        }
        return new PlaysAuthor(new PlaysAuthorKey(playsAuthorDTO.getIdAuthor(), playsAuthorDTO.getIdPlay()));
    }

    @Override
    public PlaysAuthorDTO toPlaysAuthorDTO(PlaysAuthor playsAuthor) {
        if ( playsAuthor == null ) {
            return null;
        }
        return new PlaysAuthorDTO(playsAuthor.getKey().getIdAuthor(), playsAuthor.getKey().getIdPlay());
    }
}
