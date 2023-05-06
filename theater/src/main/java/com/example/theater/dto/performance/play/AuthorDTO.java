package com.example.theater.dto.performance.play;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AuthorDTO {
    int id;
    String fullName;
    Date birthday;
    String country;
}
