package com.example.theater.dao.entity.employee;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "worker")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Worker {
    @Id
    @Column(name = "id_employee")
    @EqualsAndHashCode.Include
    int idEmployee;
    @Column(name = "category")
    String category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    Employee employee;
}
