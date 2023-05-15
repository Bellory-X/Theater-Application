package com.example.theater.dto.performance.play;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PlayDTO {
    int id;
    String name;
    Date data;
    int rating;
    String genre;
}
