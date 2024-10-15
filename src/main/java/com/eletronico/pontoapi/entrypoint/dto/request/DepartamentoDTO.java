package com.eletronico.pontoapi.entrypoint.dto.request;

import com.eletronico.pontoapi.core.domain.Departamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DepartamentoDTO implements Serializable {
    @JsonIgnore
    private List<Departamento> departamentos;

    private Integer id_departamento;
    private String name;
    private Boolean status;
}
