package com.eletronico.pontoapi.core.user.utils;

import com.eletronico.pontoapi.core.user.domain.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> isActive(Boolean active) {
        return null;
    }

    public static Specification<User> isInactive(Boolean inactive) {
        return null;
    }

    public static Specification<User> isBoth(Boolean both) {

        return null;
    }


}
