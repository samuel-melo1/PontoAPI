package com.eletronico.pontoapi.core.position.dto;

import com.eletronico.pontoapi.core.position.domain.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PositionDTO {

    private List<Position> positions;
}
