package com.example.theater.services.emploees.categories;

import com.example.theater.dao.entities.emploees.categories.MusicianCategory;
import com.example.theater.dao.repositories.emploees.categories.MusicianCategoryRepository;
import com.example.theater.dto.emploees.categories.MusicianCategoryDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.categories.MusicianCategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class MusicianCategoryService {
    private final MusicianCategoryRepository repository;
    private final MusicianCategoryMapper mapper;

    public List<MusicianCategoryDTO> getAll() {
        Iterable<MusicianCategory> iterable = repository.findAll();;

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toMusicianCategoryDTO)
                .collect(Collectors.toList());
    }

    public void add(MusicianCategoryDTO musicianCategoryDTO) {
        MusicianCategory musicianCategory = mapper.toMusicianCategory(musicianCategoryDTO);
        repository.save(musicianCategory);
    }

    public void edit(MusicianCategoryDTO musicianCategoryDTO) {
        if (!repository.existsById(musicianCategoryDTO.getCategory()))
            throw new RecordNotFoundException("Not found " + musicianCategoryDTO.getCategory());

        MusicianCategory musicianCategory = mapper.toMusicianCategory(musicianCategoryDTO);
        repository.save(musicianCategory);
    }

    public void drop(MusicianCategoryDTO musicianCategoryDTO) {
        if (!repository.existsById(musicianCategoryDTO.getCategory()))
            throw new RecordNotFoundException("Not found " + musicianCategoryDTO.getCategory());

        MusicianCategory musicianCategory = mapper.toMusicianCategory(musicianCategoryDTO);
        repository.delete(musicianCategory);
    }
}
