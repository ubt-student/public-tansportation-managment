package org.pbm.authentication.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean admin;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    @Lob
    private String avatar;
}
