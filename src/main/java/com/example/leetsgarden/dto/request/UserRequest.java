package com.example.leetsgarden.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UserRequest {
    private Long id;

    private String username;

    private String password;

    private String name;

    private String fieldType;
}
