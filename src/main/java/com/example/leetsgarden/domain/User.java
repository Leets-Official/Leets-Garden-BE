package com.example.leetsgarden.domain;
import com.example.leetsgarden.dto.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    @JsonIgnore
    private String name;

    @Column
    @JsonIgnore
    private String fieldType;

//    @Enumerated(EnumType.STRING)
    private String roles;

    public void setRoles(String roles) {
        this.roles = roles;
//        role.forEach(o -> o.setUser(this));
    }
}

