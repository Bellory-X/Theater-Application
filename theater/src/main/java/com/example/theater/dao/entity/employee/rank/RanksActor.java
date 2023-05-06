package com.example.theater.dao.entity.employee.rank;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ranks_actor")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RanksActor {
    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "id_employee")
    int idEmployee;
    @Column(name = "id_rank")
    int idRank;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rank")
    Rank rank;
}
