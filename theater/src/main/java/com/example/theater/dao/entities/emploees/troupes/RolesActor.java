package com.example.theater.dao.entities.emploees.troupes;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RolesActor")
//@IdClass(RolesActorKey.class)
@EqualsAndHashCode
public class RolesActor {
//    @Id
//    @Column(name = "IdRole")
//    int id;
//    @Id
//    @Column(name = "IdEmployee")
//    int idEmployee;
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "IdRole")),
            @AttributeOverride(name = "idEmployee", column = @Column(name = "IdEmployee"))
    })
    RolesActorKey key;
}
