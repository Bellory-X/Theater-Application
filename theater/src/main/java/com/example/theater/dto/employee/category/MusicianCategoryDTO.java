package com.example.theater.dto.employee.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MusicianCategoryDTO {
    int id;
    String category;
}
