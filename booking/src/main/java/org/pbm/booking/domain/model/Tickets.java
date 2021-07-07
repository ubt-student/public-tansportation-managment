package org.pbm.booking.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userEmail;
    private Double amount;
    @ManyToOne
    private Travel travel;

}
