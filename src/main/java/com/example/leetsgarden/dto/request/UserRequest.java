package com.example.leetsgarden.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private Long id;

    private String userid;

    private String password;

    private String name;

    private String teamType;

    private String major;

    private Integer studentNumber;
}
