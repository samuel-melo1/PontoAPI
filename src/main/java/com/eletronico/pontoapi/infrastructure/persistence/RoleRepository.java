package com.eletronico.pontoapi.infrastructure.persistence;

import com.eletronico.pontoapi.core.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
