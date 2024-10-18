package com.eletronico.pontoapi.entrypoint.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class RoleDTO {
    private Integer id_role;
    private String name;
    public RoleDTO(){}
    public RoleDTO(Integer id_role, String name) {
        this.id_role = id_role;
        this.name = name;
    }
    public Integer getId_role() {
        return id_role;
    }
    public void setId_role(Integer id_role) {
        this.id_role = id_role;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
