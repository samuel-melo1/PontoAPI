package com.eletronico.pontoapi.core.dto;

import com.eletronico.pontoapi.core.enums.UserRole;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public record LoginResponseDTO(String token, Collection details) { }
