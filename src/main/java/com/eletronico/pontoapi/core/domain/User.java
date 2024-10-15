package com.eletronico.pontoapi.core.domain;

import com.eletronico.pontoapi.core.enums.UserRole;
import com.eletronico.pontoapi.entrypoint.dto.request.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
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
public class User implements  Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;

    @Column(name = "email", unique = true)
    private String email;
    private String name;
    private String password;
    private String telefone;
    @Column(name = "cpf", unique = true)
    @CPF
    private String cpf;
    private Boolean status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_users_role",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Role> permissions = new ArrayList<>();

    public List<String> getRoles(){
        List<String> roles = new ArrayList<>();
        for (Role permission : permissions){
            roles.add(permission.getName());
        }
        return roles;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//      return this.permissions;
//     }
//    @Override
//    public String getUsername() {
//        return email;
//    }
//    @Override
//    public String getPassword() {
//        return password;
//    }
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
