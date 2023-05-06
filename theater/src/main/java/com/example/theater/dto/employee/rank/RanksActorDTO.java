package com.example.theater.dto.employee.rank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RanksActorDTO {
    int id;
    int idEmployee;
    int idRank;
}
