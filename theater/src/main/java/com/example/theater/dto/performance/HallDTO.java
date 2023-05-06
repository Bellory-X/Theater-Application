package com.example.theater.dto.performance;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class HallDTO {
    int id;
    Date start;
    Date end;
    String name;
    int idPerformance;
}
