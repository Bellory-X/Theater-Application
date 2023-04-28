package com.example.theater.dao.entities.emploees.charecters;

import com.example.theater.dao.entities.emploees.Employee;
import jakarta.persistence.*;
import lombok.*;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CharactersWorker")
@Getter
@Setter
@IdClass(CharactersWorkerKey.class)
public class CharactersWorker {
    @Id
    @Column(name = "IdEmployee")
    int id;
    @Id
    @Column(name = "character")
    String character;
    @OneToOne(mappedBy = "id")
    Employee employee;
    @OneToOne(mappedBy = "character")
    WorkerCharacter workerCharacter;
}
