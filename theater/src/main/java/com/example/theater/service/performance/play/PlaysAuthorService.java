package com.example.theater.service.performance.play;

import com.example.theater.dao.entity.performance.play.PlaysAuthor;
import com.example.theater.dao.repository.performance.play.PlaysAuthorRepository;
import com.example.theater.dto.performance.play.PlaysAuthorDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.play.PlaysAuthorMapper;
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
        if (!repository.existsById(playsAuthorDTO.getId()))
            throw new RecordNotFoundException("Not found " + playsAuthorDTO.getIdAuthor());

        PlaysAuthor playsAuthor = mapper.toPlaysAuthor(playsAuthorDTO);
        repository.save(playsAuthor);
    }

    public void drop(PlaysAuthorDTO playsAuthorDTO) {
        if (!repository.existsById(playsAuthorDTO.getId()))
            throw new RecordNotFoundException("Not found " + playsAuthorDTO.getIdAuthor());

        PlaysAuthor playsAuthor = mapper.toPlaysAuthor(playsAuthorDTO);
        repository.delete(playsAuthor);
    }
}
