package com.eletronico.pontoapi.utils;

import com.eletronico.pontoapi.application.gateways.UserService;
import com.eletronico.pontoapi.core.domain.Role;
import com.eletronico.pontoapi.core.enums.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;
import java.util.Objects;
public class GenericValidAdministrator {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class.getName());
    public static void verify(List<Role> permissions, Throwable destination){
        for (Role roles: permissions){
            var auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().
                    stream().map(GrantedAuthority::getAuthority).toList();
            if (roles != null && roles.getName().equals(UserRole.ADMINISTRADOR.name()) && !Objects.equals(auth.get(0), UserRole.ADMINISTRADOR.name())) {
              throw new RuntimeException(destination);
            }
        }
    }
}
