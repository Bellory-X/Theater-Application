package com.example.theater.dto.performances.plays;

import lombok.Value;

import java.util.Date;

@Value
public class AuthorDTO {
    int id;
    String fullName;
    Date birthday;
    String country;
}
