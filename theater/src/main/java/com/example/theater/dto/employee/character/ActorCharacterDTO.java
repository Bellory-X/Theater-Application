package com.example.theater.dto.employee.character;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActorCharacterDTO {
    int id;
    String character;
}
