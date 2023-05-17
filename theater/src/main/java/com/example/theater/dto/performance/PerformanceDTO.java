package com.example.theater.dto.performance;

import com.example.theater.dto.performance.play.PlayDTO;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PerformanceDTO {
    int id;
    int idPlay;
    int idRepertoire;
    int price;
    String theater;

    String name;
    Date data;
    int rating;
    String genre;

    int number;
    String to_theater;
}
