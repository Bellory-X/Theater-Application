package com.example.theater.services.emploees;

import com.example.theater.dao.entities.emploees.Director;
import com.example.theater.dao.repositories.emploees.DirectorRepository;
import com.example.theater.dto.emploees.DirectorDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.DirectorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DirectorService {
    DirectorRepository repository;
    DirectorMapper mapper;

    public List<DirectorDTO> getAll() {
        Iterable<Director> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toDirectorDTO)
                .collect(Collectors.toList());
    }

    public void add(DirectorDTO directorDTO) {
        Director director = mapper.toDirector(directorDTO);
        repository.save(director);
    }

    public void edit(DirectorDTO directorDTO) {
        if (!repository.existsById(directorDTO.getId()))
            throw new RecordNotFoundException("Not found " + directorDTO.getId());

        Director director = mapper.toDirector(directorDTO);
        repository.save(director);
    }

    public void drop(DirectorDTO directorDTO) {
        if (!repository.existsById(directorDTO.getId()))
            throw new RecordNotFoundException("Not found " + directorDTO.getId());

        Director director = mapper.toDirector(directorDTO);
        repository.delete(director);
    }
}
