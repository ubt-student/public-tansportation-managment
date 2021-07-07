package org.pbm.map.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Municipality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer code;
    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "name_sq")
    private String nameSq;
    @Column(name = "name_sr")
    private String nameSr;
}
