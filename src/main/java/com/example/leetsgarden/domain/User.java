package com.example.leetsgarden.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false)
    @JsonIgnore
    private String fieldType;

    @Enumerated(EnumType.STRING)
    private Role roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMeeting> userMeetings;

    public void setRoles(Role roles) {
        this.roles = roles;
    }
}

