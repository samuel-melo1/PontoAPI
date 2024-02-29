package com.eletronico.pontoapi.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_user;

    @Column(name = "username")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "status")
    private Boolean status;

}
