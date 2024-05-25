package com.eletronico.pontoapi.core.position.dto;

import com.eletronico.pontoapi.core.position.domain.Position;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @NotEmpty
    private List<Position> positions;
}
