package com.example.leetsgarden.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean isAttended;

    @ManyToOne
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Meeting meeting;

    public Attendance(User user, Meeting meeting, boolean isAttended) {
        this.user = user;
        this.meeting = meeting;
        this.isAttended = isAttended;
    }

    public Attendance(User user, Meeting meeting) {
        this.user = user;
        this.meeting = meeting;
    }
}