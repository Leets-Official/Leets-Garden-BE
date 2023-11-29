package com.example.leetsgarden.dto.request;

import lombok.Getter;

@Getter
public class AddUserRequest {

    private String username;

    private String password;

    private String name;

    private String fieldType;
}
