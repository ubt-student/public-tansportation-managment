package org.pbm.booking.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String agency;
    @Column(name = "depart_time")
    private String departTime;
    @Column(name = "arrival_time")
    private String arrivalTime;
    @ManyToOne
    private Route route;
    private Double price;

}
