package com.example.theater.services.performances.plays;

import com.example.theater.dao.entities.performances.plays.PlaysAuthor;
import com.example.theater.dao.entities.performances.plays.PlaysAuthorKey;
import com.example.theater.dao.repositories.performances.plays.PlaysAuthorRepository;
import com.example.theater.dto.performances.plays.PlaysAuthorDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.performances.plays.PlaysAuthorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PlaysAuthorService {
    private final PlaysAuthorRepository repository;
    private final PlaysAuthorMapper mapper;

    public List<PlaysAuthorDTO> getAll() {
        Iterable<PlaysAuthor> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toPlaysAuthorDTO)
                .collect(Collectors.toList());
    }

    public void add(PlaysAuthorDTO playsAuthorDTO) {
        PlaysAuthor playsAuthor = mapper.toPlaysAuthor(playsAuthorDTO);
        repository.save(playsAuthor);
    }

    public void edit(PlaysAuthorDTO playsAuthorDTO) {
        if (!repository.existsById(new PlaysAuthorKey(playsAuthorDTO.getIdAuthor(), playsAuthorDTO.getIdPlay())))
            throw new RecordNotFoundException("Not found " + playsAuthorDTO.getIdAuthor());

        PlaysAuthor playsAuthor = mapper.toPlaysAuthor(playsAuthorDTO);
        repository.save(playsAuthor);
    }

    public void drop(PlaysAuthorDTO playsAuthorDTO) {
        if (!repository.existsById(new PlaysAuthorKey(playsAuthorDTO.getIdAuthor(), playsAuthorDTO.getIdPlay())))
            throw new RecordNotFoundException("Not found " + playsAuthorDTO.getIdAuthor());

        PlaysAuthor playsAuthor = mapper.toPlaysAuthor(playsAuthorDTO);
        repository.delete(playsAuthor);
    }
}
