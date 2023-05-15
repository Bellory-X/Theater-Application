package com.example.theater.dto.performance.play;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PlaysAuthorDTO {
    int id;
    int idAuthor;
    int idPlay;
}
