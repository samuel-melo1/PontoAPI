package com.eletronico.pontoapi.adapters.database.role;

import com.eletronico.pontoapi.core.role.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
