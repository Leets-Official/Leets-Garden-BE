package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.User;
import com.example.leetsgarden.dto.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private String name;
    private String fieldType;
    private Role roles;
    private String token;
    private Boolean result;
    private String message;

    public UserResponse(User user, Boolean result, String message) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.fieldType = user.getFieldType();
        this.roles = user.getRoles();
        this.result = result;
        this.message = message;

    }
}
