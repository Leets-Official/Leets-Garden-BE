package com.example.leetsgarden.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public enum Role {
    MASTER("ROLE_MASTER,ROLE_ADMIN,ROLE_USER"),
    ADMIN("ROLE_ADMIN,ROLE_USER"),
    USER("ROLE_USER");
    private String role;
    Role(String role) {
        this.role = role;
    }
    public ArrayList<String> value() {
        return new ArrayList<>(Arrays.asList(this.role.split(",")));
    }
}