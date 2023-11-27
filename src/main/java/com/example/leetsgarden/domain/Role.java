package com.example.leetsgarden.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public enum Role {
    MASTER("ROLE_MASTER,ROLE_ADMIN,ROLE_USER"),
    ADMIN("ROLE_ADMIN,ROLE_USER"),
    USER("ROLE_USER");
    private String role;
    Role(String role) {
        this.role = role;
    }
    public List<String> value() {
        return Collections.unmodifiableList(Arrays.asList(this.role.split(",")));

    }
}