package com.example.theater.dao.entities.emploees.troupes;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MusiciansPerformance")
//@IdClass(MusiciansPerformanceKey.class)
@EqualsAndHashCode
public class MusiciansPerformance {
//    @Id
//    @Column(name = "IdEmployee")
//    int idEmployee;
//    @Column(name = "IdPerformance")
//    int idPerformance;
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "idPerformance", column = @Column(name = "IdPerformance")),
            @AttributeOverride(name = "idEmployee", column = @Column(name = "IdEmployee"))
    })
    MusiciansPerformanceKey key;
}
