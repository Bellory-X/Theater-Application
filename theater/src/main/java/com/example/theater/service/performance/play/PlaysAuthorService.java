package com.example.theater.service.performance.play;

import com.example.theater.dao.entity.performance.play.PlaysAuthor;
import com.example.theater.dao.repository.performance.play.AuthorRepository;
import com.example.theater.dao.repository.performance.play.PlaysAuthorRepository;
import com.example.theater.dto.performance.play.PlaysAuthorDTO;
import com.example.theater.exception.QueryException;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.play.PlaysAuthorMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PlaysAuthorService {
    private final PlaysAuthorRepository repository;
    private final PlaysAuthorMapper mapper;

    public Optional<PlaysAuthorDTO> getById(int id) {
        return repository.findById(id).map(mapper::toPlaysAuthorDTO);
    }

    public List<PlaysAuthorDTO> getAll() {
        Iterable<PlaysAuthor> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toPlaysAuthorDTO)
                .collect(Collectors.toList());
    }

    public void add(PlaysAuthorDTO dto) {
        try {
            dto.setId(Generator.generateId());
            PlaysAuthor playsAuthor = mapper.toPlaysAuthor(dto);
            repository.save(playsAuthor);
        } catch (DataAccessException e) {
            throw new QueryException("Recheck fields maybe it exist");
        }
    }

    public void edit(PlaysAuthorDTO dto) {
        try {
            if (!repository.existsById(dto.getId()))
                throw new RecordNotFoundException("Not found " + dto.getIdAuthor());

            PlaysAuthor playsAuthor = mapper.toPlaysAuthor(dto);
            repository.save(playsAuthor);
        } catch (DataAccessException e) {
            throw new QueryException("Recheck fields maybe it exist");
        }
    }

    public void drop(PlaysAuthorDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getIdAuthor());

        PlaysAuthor playsAuthor = mapper.toPlaysAuthor(dto);
        repository.delete(playsAuthor);
    }
}
