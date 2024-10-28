package com.eletronico.pontoapi.infrastructure.persistence;

import com.eletronico.pontoapi.core.domain.RegistroPonto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroPontoRepository  extends JpaRepository<RegistroPonto, Integer> {
}
