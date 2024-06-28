package com.eletronico.pontoapi.entrypoint.dto.response;

import java.util.List;
public record LoginResponseDTO(String token, List<String> roles) { }
