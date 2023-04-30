package com.example.theater.services.performances;

import com.example.theater.dao.entities.performances.Place;
import com.example.theater.dao.repositories.performances.PlaceRepository;
import com.example.theater.dto.performances.PlaceDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.performances.PlaceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PlaceService {
    private final PlaceRepository repository;
    private final PlaceMapper mapper;

    public List<PlaceDTO> getAll() {
        Iterable<Place> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toPlaceDTO)
                .collect(Collectors.toList());
    }

    public void add(PlaceDTO placeDTO) {
        Place place = mapper.toPlace(placeDTO);
        repository.save(place);
    }

    public void edit(PlaceDTO placeDTO) {
        if (!repository.existsById(placeDTO.getId()))
            throw new RecordNotFoundException("Not found " + placeDTO.getId());

        Place place = mapper.toPlace(placeDTO);
        repository.save(place);
    }

    public void drop(PlaceDTO placeDTO) {
        if (!repository.existsById(placeDTO.getId()))
            throw new RecordNotFoundException("Not found " + placeDTO.getId());

        Place place = mapper.toPlace(placeDTO);
        repository.delete(place);
    }
}
