package com.example.theater.dto.performance.troupe;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DirectorsPerformanceDTO {
    int id;
    int idPerformance;
    int idEmployee;

    String fullName;
    String category;
}
