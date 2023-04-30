package com.example.theater.dao.entities.emploees.ranks;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Rank")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Rank {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "Name")
    String name;
    @Column(name = "Contest")
    String contest;
    @Column(name = "Data")
    Date data;
}
