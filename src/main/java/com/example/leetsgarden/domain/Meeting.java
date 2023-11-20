package com.example.leetsgarden.domain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private  String type;

    @Column
    private String place;

    @Column
    private String content;

    @Column
    private  String color;

    @ManyToMany(mappedBy = "meeting", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<User> users;


}
