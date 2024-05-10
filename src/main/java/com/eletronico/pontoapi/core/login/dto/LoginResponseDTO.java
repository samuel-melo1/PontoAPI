package com.eletronico.pontoapi.core.login.dto;

import java.util.Collection;

public record LoginResponseDTO(String token, Collection details) { }
