package com.example.theater.service.performance.play;

import com.example.theater.dao.entity.performance.play.Genre;
import com.example.theater.dao.repository.performance.play.GenreRepository;
import com.example.theater.dto.performance.play.GenreDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.play.GenreMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class GenreService {
    private final GenreRepository repository;
    private final GenreMapper mapper;

    public Optional<GenreDTO> getById(int id) {
        return repository.findById(id).map(mapper::toGenreDTO);
    }

    public List<GenreDTO> getAll() {
        Iterable<Genre> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toGenreDTO)
                .collect(Collectors.toList());
    }

    public void add(GenreDTO dto) {
        dto.setId(Generator.generateId());
        Genre genre = mapper.toGenre(dto);
        repository.save(genre);
    }

    public void edit(GenreDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Genre genre = mapper.toGenre(dto);
        repository.save(genre);
    }

    public void drop(GenreDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Genre genre = mapper.toGenre(dto);
        repository.delete(genre);
    }
}
