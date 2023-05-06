package com.example.theater.dto.performance.play;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaysAuthorDTO {
    int id;
    int idAuthor;
    int idPlay;
}
