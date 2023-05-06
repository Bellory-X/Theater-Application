package com.example.theater.dto.performance.play;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenreDTO {
    int id;
    String name;
}
