package com.example.theater.dto.employee.rank;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RanksActorDTO {
    int id;
    int idEmployee;
    int idRank;

    String name;
    String contest;
    Date data;
}
