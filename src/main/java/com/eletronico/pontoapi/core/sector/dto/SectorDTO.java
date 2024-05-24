package com.eletronico.pontoapi.core.sector.dto;

import com.eletronico.pontoapi.core.sector.domain.Sector;
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
public class SectorDTO implements Serializable {
    private List<Sector> sectors;

}
