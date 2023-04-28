package com.example.theater.dao.entities.emploees.charecters;

import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@IdClass(CharactersWorkerKey.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharactersWorkerKey implements Serializable {
    int id;
    String character;
}