package com.example.leetsgarden.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Role {
    ADMIN("ROLE_ADMIN,ROLE_USER"),
    USER("ROLE_USER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public List<String> value() {
        return Collections.unmodifiableList(Arrays.asList(this.role.split(",")));

    }
}