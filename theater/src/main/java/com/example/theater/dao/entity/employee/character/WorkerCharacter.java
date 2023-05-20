package com.example.theater.dao.entity.employee.character;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "worker_character")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WorkerCharacter {
    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "character")
    String character;
}
