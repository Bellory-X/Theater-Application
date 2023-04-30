package com.example.theater.dao.entities.emploees.troupes;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DirectorsPerformance")
//@IdClass(DirectorsPerformanceKey.class)
@EqualsAndHashCode
public class DirectorsPerformance {
//    @Id
//    @Column(name = "IdPerformance")
//    int idPerformance;
//    @Id
//    @Column(name = "IdEmployee")
//    int idEmployee;
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "idPerformance", column = @Column(name = "IdPerformance")),
            @AttributeOverride(name = "idEmployee", column = @Column(name = "IdEmployee"))
    })
    DirectorsPerformanceKey key;
}
