package com.example.theater.dto.employee.rank;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RankDTO {
    int id;
    String name;
    String contest;
    Date data;
}
