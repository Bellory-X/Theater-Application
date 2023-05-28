package com.example.theater.service.performance.play;

import com.example.theater.dao.entity.performance.play.Author;
import com.example.theater.dao.repository.performance.play.AuthorRepository;
import com.example.theater.dto.performance.PerformanceDTO;
import com.example.theater.dto.performance.play.AuthorDTO;
import com.example.theater.exception.QueryException;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.play.AuthorMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository repository;
    private final AuthorMapper mapper;

    public Optional<AuthorDTO> getById(int id) {
        return repository.findById(id).map(mapper::toAuthorDTO);
    }

    public List<AuthorDTO> getAll() {
        Iterable<Author> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toAuthorDTO)
                .collect(Collectors.toList());
    }

    public void add(AuthorDTO dto) {
        try {
            dto.setId(Generator.generateId());
            Author author = mapper.toAuthor(dto);
            repository.save(author);
        } catch (DataAccessException e) {
            throw new QueryException("Recheck fields maybe it exist");
        }
    }

    public void edit(AuthorDTO dto) {
        try {
            if (!repository.existsById(dto.getId()))
                throw new RecordNotFoundException("Not found " + dto.getId());

            Author author = mapper.toAuthor(dto);
            repository.save(author);
        } catch (DataAccessException e) {
            throw new QueryException("Recheck fields maybe it exist");
        }
    }

    public void drop(AuthorDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Author author = mapper.toAuthor(dto);
        repository.delete(author);
    }

    public List<AuthorDTO> findActorQuery4(Date birthday1, Date birthday2, String genre, String theater,
                                                String country, int start1, int start2) {
        return repository.findActorQuery4(birthday1, birthday2, genre, theater, country, start1, start2).stream()
                .map(mapper::toAuthorDTO)
                .toList();
    }
}
