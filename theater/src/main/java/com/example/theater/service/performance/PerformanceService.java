package com.example.theater.service.performance;

import com.example.theater.dao.entity.performance.Performance;
import com.example.theater.dao.repository.performance.PerformanceRepository;
import com.example.theater.dto.performance.PerformanceDTO;
import com.example.theater.dto.performance.PlaceDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.PerformanceMapper;
import com.example.theater.service.Generator;
import com.example.theater.service.performance.play.PlayService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PerformanceService {
    private final PerformanceRepository repository;
    private final PerformanceMapper mapper;
    private final PlayService playService;

    public Optional<PerformanceDTO> getById(int id) {
        return repository.findById(id).map(mapper::toPerformanceDTO);
    }

    public List<PerformanceDTO> getAll() {
        Iterable<Performance> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(el -> {
                    PerformanceDTO dto = mapper.toPerformanceDTO(el);
                    playService.getById(dto.getIdPlay())
                            .ifPresent(v -> {
                                dto.setName(v.getName());
                                dto.setData(v.getData());
                                dto.setRating(v.getRating());
                                dto.setGenre(v.getGenre());
                            });
                    return dto;
                }).toList();
    }

    public void add(PerformanceDTO dto) {
        dto.setId(Generator.generateId());
        Performance performance = mapper.toPerformance(dto);
        repository.save(performance);
    }

    public void edit(PerformanceDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Performance performance = mapper.toPerformance(dto);
        repository.save(performance);
    }

    public void drop(PerformanceDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Performance performance = mapper.toPerformance(dto);
        repository.delete(performance);
    }

    public List<PerformanceDTO> findActorQuery2(Date birthday1, Date birthday2, String genre, String theater) {
        List<Performance> list = repository.findActorQuery2(birthday1, birthday2, genre, theater);
        System.out.println(list.size());
        return list.stream().map(el -> {
                    PerformanceDTO dto = mapper.toPerformanceDTO(el);
                    playService.getById(dto.getIdPlay())
                            .ifPresent(v -> {
                                dto.setName(v.getName());
                                dto.setData(v.getData());
                                dto.setRating(v.getRating());
                                dto.setGenre(v.getGenre());
                            });
                    return dto;
                }).toList();
    }

    public List<PerformanceDTO> findActorQuery3(Date birthday1, Date birthday2, String genre, String theater) {
        return repository.findActorQuery3(birthday1, birthday2, genre, theater)
                .stream().map(el -> {
                    PerformanceDTO dto = mapper.toPerformanceDTO(el);
                    playService.getById(dto.getIdPlay())
                            .ifPresent(v -> {
                                dto.setName(v.getName());
                                dto.setData(v.getData());
                                dto.setRating(v.getRating());
                                dto.setGenre(v.getGenre());
                            });
                    return dto;
                }).toList();
    }

    public List<PerformanceDTO> findActorQuery5(Date birthday1, Date birthday2, String genre, String theater, String country,
                                                String fullName, int year1, int year2) {
        return repository.findActorQuery5( birthday1,  birthday2,  genre,  theater, country,
                 fullName,  year1,  year2)
                .stream().map(el -> {
                    PerformanceDTO dto = mapper.toPerformanceDTO(el);
                    playService.getById(dto.getIdPlay())
                            .ifPresent(v -> {
                                dto.setName(v.getName());
                                dto.setData(v.getData());
                                dto.setRating(v.getRating());
                                dto.setGenre(v.getGenre());
                            });
                    return dto;
                }).toList();
    }
}
