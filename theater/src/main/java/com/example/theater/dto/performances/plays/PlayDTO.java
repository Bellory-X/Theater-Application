package com.example.theater.dto.performances.plays;

import lombok.Value;

import java.util.Date;

@Value
public class PlayDTO {
    int id;
    String name;
    Date data;
    String rating;
    String genre;
}
