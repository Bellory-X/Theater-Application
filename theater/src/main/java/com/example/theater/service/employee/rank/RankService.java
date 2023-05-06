package com.example.theater.service.employee.rank;

import com.example.theater.dao.entity.employee.rank.Rank;
import com.example.theater.dao.repository.employee.rank.RankRepository;
import com.example.theater.dto.employee.rank.RankDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.rank.RankMapper;
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
