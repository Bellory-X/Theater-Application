package com.example.theater.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
public class TheaterDTO implements Serializable {
    private int id;
    private String name;
}
