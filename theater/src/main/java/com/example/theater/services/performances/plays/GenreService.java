package com.example.theater.services.performances.plays;

import com.example.theater.dao.entities.performances.plays.Genre;
import com.example.theater.dao.repositories.performances.plays.GenreRepository;
import com.example.theater.dto.performances.plays.GenreDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.performances.plays.GenreMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class GenreService {
    private final GenreRepository repository;
    private final GenreMapper mapper;

    public List<GenreDTO> getAll() {
        Iterable<Genre> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toGenreDTO)
                .collect(Collectors.toList());
    }

    public void add(GenreDTO genreDTO) {
        Genre genre = mapper.toGenre(genreDTO);
        repository.save(genre);
    }

    public void edit(GenreDTO genreDTO) {
        if (!repository.existsById(genreDTO.getName()))
            throw new RecordNotFoundException("Not found " + genreDTO.getName());

        Genre genre = mapper.toGenre(genreDTO);
        repository.save(genre);
    }

    public void drop(GenreDTO genreDTO) {
        if (!repository.existsById(genreDTO.getName()))
            throw new RecordNotFoundException("Not found " + genreDTO.getName());

        Genre genre = mapper.toGenre(genreDTO);
        repository.delete(genre);
    }
}
