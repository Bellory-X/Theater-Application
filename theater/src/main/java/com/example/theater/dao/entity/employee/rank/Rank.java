package com.example.theater.dao.entity.employee.rank;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "rank")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Rank {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "contest")
    String contest;
    @Column(name = "data")
    Date data;
}
