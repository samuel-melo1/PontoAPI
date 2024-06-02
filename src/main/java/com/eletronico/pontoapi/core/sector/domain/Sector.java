package com.eletronico.pontoapi.core.sector.domain;

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

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sector implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_sector;
    private String name;
    private Boolean status;
    @JsonIgnore
    @OneToMany(mappedBy = "sector")
    private List<User> users = new ArrayList<>();
}
