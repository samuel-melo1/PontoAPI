package com.eletronico.pontoapi.core.user.domain;

import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.user.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "users")
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;

    @Column(name = "email")
    private String email;
    private String name;
    private String password;
    private String telefone;
    @Column(name = "cpf", length = 14)
    private String cpf;
    private Boolean status;
    private UserRole userRole;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userRole == UserRole.ADMINISTRADOR) {
            return List.of(new SimpleGrantedAuthority("ADMINISTRADOR"), new SimpleGrantedAuthority("GESTOR"), new SimpleGrantedAuthority("COLABORADOR"));
        } else if (this.userRole == UserRole.GESTOR) {
            return List.of(new SimpleGrantedAuthority("GESTOR"), new SimpleGrantedAuthority("COLABORADOR"));
        } else {
            return List.of(new SimpleGrantedAuthority("COLABORADOR"));
        }
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
