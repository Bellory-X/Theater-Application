package com.example.theater.dao.entity.employee.category;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "director_category")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DirectorCategory {
    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "category")
    String category;
}
