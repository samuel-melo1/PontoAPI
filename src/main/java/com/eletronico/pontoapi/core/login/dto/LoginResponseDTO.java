package com.eletronico.pontoapi.core.login.dto;

import java.util.List;
public record LoginResponseDTO(String token, List<String> roles) { }
