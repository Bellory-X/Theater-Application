package com.example.theater.dao.entities.emploees.ranks;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RanksActor")
//@IdClass(RanksActorKey.class)
@EqualsAndHashCode
public class RanksActor {
//    @Id
//    @Column(name = "IdEmployee")
//    int id;
//    @Id
//    @Column(name = "IdRank")
//    int idRank;
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "IdEmployee")),
            @AttributeOverride(name = "idRank", column = @Column(name = "IdRank"))
    })
    RanksActorKey key;
}
