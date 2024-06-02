package com.eletronico.pontoapi.core.position.domain;

import com.eletronico.pontoapi.core.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Position implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_position;
    private String name;
    private Boolean status;
    @JsonIgnore
    @OneToMany(mappedBy = "position")
    private List<User> users = new ArrayList<>();
}
