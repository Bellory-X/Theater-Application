package com.example.theater.dao.entities.emploees.characters;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//@IdClass(CharactersDirectorKey.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CharactersDirectorKey implements Serializable {
    int id;
    String character;
}