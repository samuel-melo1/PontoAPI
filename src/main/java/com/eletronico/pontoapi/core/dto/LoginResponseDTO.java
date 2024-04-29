package com.eletronico.pontoapi.core.dto;

import java.util.Collection;

public record LoginResponseDTO(String token, Collection details) { }
