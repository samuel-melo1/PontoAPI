package com.eletronico.pontoapi.adapters.database;

import com.eletronico.pontoapi.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsPessoaByEmail(String email);
    UserDetails findByEmail(String email);
}
