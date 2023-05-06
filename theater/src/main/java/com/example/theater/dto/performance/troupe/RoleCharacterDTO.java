package com.example.theater.dto.performance.troupe;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleCharacterDTO {
    int id;
    int idRole;
    String character;
}
