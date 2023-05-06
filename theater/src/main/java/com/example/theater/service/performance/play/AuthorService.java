package com.example.theater.service.performance.play;

import com.example.theater.dao.entity.performance.play.Author;
import com.example.theater.dao.repository.performance.play.AuthorRepository;
import com.example.theater.dto.performance.play.AuthorDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.play.AuthorMapper;
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
