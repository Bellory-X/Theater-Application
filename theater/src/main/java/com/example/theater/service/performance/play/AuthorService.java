package com.example.theater.service.performance.play;

import com.example.theater.dao.entity.performance.play.Author;
import com.example.theater.dao.repository.performance.play.AuthorRepository;
import com.example.theater.dto.performance.play.AuthorDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.play.AuthorMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        dto.setId(Generator.generateId());
        Author author = mapper.toAuthor(dto);
        repository.save(author);
    }

    public void edit(AuthorDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Author author = mapper.toAuthor(dto);
        repository.save(author);
    }

    public void drop(AuthorDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Author author = mapper.toAuthor(dto);
        repository.delete(author);
    }
}
