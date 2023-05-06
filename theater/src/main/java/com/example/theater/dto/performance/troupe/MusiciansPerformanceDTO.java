package com.example.theater.dto.performance.troupe;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MusiciansPerformanceDTO {
    int id;
    int idPerformance;
    int idEmployee;
}
