package com.example.theater.dao.entity.employee.category;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "MusicianCategory")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MusicianCategory {
    @Id
    @Column(name = "Id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "category")
    String category;
}
