package com.example.theater.dao.entity.employee.category;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "musician_category")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MusicianCategory {
    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "category")
    String category;
}
