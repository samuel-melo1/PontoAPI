package com.eletronico.pontoapi.core.domain;

import com.eletronico.pontoapi.core.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.util.ArrayList;
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

    @Column(name = "email", unique = true)
    private String email;
    private String name;
    private String password;
    private String telefone;
    @Column(name = "cpf", length = 14, unique = true)
    private String cpf;
    private Boolean status;
    @JsonIgnore
    private UserRole userRole;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private Position position;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sector_id")
    private Sector sector;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_users_role",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> permissions = new ArrayList<>();

    @OneToMany
    private List<RegistroPonto> pontos = new ArrayList<>();
    public List<String> getRoles(){
        List<String> roles = new ArrayList<>();
        for (Role permission : permissions){
            roles.add(permission.getName());
        }
        return roles;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return this.permissions;
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
        return this.accountNonExpired;
    }
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
