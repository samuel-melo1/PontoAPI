package com.eletronico.pontoapi.adapters.database.user;

import com.eletronico.pontoapi.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
    boolean existsPessoaByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email =:email")
    User findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findUserByEmail(String email);

}
