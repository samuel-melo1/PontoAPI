package com.eletronico.pontoapi.core.domain;

import com.eletronico.pontoapi.core.enums.TipoRegistroPonto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegistroPonto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ponto;

    private LocalDateTime dateRegister;
    private String currentLocation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User id_user;
    private LocalDateTime dateUpdate;
    private TipoRegistroPonto type;
}
