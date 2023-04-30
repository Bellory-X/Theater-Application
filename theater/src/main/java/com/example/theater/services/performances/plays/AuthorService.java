package com.example.theater.services.performances.plays;

import com.example.theater.dao.entities.performances.plays.Author;
import com.example.theater.dao.repositories.performances.plays.AuthorRepository;
import com.example.theater.dto.performances.plays.AuthorDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.performances.plays.AuthorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository repository;
    private final AuthorMapper mapper;

    public List<AuthorDTO> getAll() {
        Iterable<Author> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toAuthorDTO)
                .collect(Collectors.toList());
    }

    public void add(AuthorDTO authorDTO) {
        Author author = mapper.toAuthor(authorDTO);
        repository.save(author);
    }

    public void edit(AuthorDTO authorDTO) {
        if (!repository.existsById(authorDTO.getId()))
            throw new RecordNotFoundException("Not found " + authorDTO.getId());

        Author author = mapper.toAuthor(authorDTO);
        repository.save(author);
    }

    public void drop(AuthorDTO authorDTO) {
        if (!repository.existsById(authorDTO.getId()))
            throw new RecordNotFoundException("Not found " + authorDTO.getId());

        Author author = mapper.toAuthor(authorDTO);
        repository.delete(author);
    }
}
