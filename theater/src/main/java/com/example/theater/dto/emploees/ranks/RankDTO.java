package com.example.theater.dto.emploees.ranks;

import lombok.Value;

import java.util.Date;

@Value
public class RankDTO {
    int id;
    String name;
    String contest;
    Date data;
}
