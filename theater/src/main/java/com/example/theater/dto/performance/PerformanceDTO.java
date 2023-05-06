package com.example.theater.dto.performance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PerformanceDTO {
    int id;
    int idPlay;
    int idRepertoire;
    int price;
    String theater;
}
