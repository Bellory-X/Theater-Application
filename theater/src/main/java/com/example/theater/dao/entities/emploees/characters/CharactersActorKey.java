package com.example.theater.dao.entities.emploees.characters;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@IdClass(CharactersActorKey.class)
@Embeddable
public class CharactersActorKey implements Serializable {
    int id;
    String character;
}
