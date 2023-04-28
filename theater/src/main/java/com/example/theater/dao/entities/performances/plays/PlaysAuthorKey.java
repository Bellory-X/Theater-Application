package com.example.theater.dao.entities.performances.plays;

import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@IdClass(PlaysAuthorKey.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaysAuthorKey implements Serializable {
    int idAuthor;
    int idPlay;
}