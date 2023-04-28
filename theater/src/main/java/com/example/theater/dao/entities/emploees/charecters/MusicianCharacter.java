package com.example.theater.dao.entities.emploees.charecters;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MusicianCharacter")
@Getter
@Setter
public class MusicianCharacter {
    @Id
    @Column(name = "character")
    String character;
}
