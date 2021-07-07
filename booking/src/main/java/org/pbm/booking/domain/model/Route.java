package org.pbm.booking.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "municipality_id_from")
    private Long municipalityIdFrom;
    @Column(name = "municipality_id_to")
    private Long municipalityIdTo;

}
