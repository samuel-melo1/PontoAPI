package com.eletronico.pontoapi.core.sector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SectorDTO {

    private Integer code;
    private String name;

}
