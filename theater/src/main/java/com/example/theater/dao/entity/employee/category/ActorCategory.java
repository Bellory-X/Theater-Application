package com.example.theater.dao.entity.employee.category;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ActorCategory")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ActorCategory {
    @Id
    @Column(name = "Id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "category")
    String category;
}
