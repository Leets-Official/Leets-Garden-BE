package com.example.leetsgarden.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public enum Role {

    MASTER("ROLE_MASTER,ROLE_ADMIN,ROLE_USER"),
    ADMIN("ROLE_ADMIN,ROLE_USER"),
    USER("ROLE_USER");
    private String value;
}