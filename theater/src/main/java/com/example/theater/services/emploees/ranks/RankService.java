package com.example.theater.services.emploees.ranks;

import com.example.theater.dao.entities.emploees.ranks.Rank;
import com.example.theater.dao.repositories.emploees.ranks.RankRepository;
import com.example.theater.dto.emploees.ranks.RankDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.ranks.RankMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RankService {
    private final RankRepository repository;
    private final RankMapper mapper;

    public List<RankDTO> getAll() {
        Iterable<Rank> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toRankDTO)
                .collect(Collectors.toList());
    }

    public void add(RankDTO rankDTO) {
        Rank rank = mapper.toRank(rankDTO);
        repository.save(rank);
    }

    public void edit(RankDTO rankDTO) {
        if (!repository.existsById(rankDTO.getId()))
            throw new RecordNotFoundException("Not found " + rankDTO.getId());

        Rank rank = mapper.toRank(rankDTO);
        repository.save(rank);
    }

    public void drop(RankDTO rankDTO) {
        if (!repository.existsById(rankDTO.getId()))
            throw new RecordNotFoundException("Not found " + rankDTO.getId());

        Rank rank = mapper.toRank(rankDTO);
        repository.delete(rank);
    }
}
