package com.example.leetsgarden.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AttendanceType attType = AttendanceType.ABSENCE;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "weeklyMeetings_id")
    private WeeklyMeetings weeklyMeetings;

    public Attendance(UserMeeting userMeeting, WeeklyMeetings weeklyMeetings) {
        this.user = userMeeting.getUser();
        this.weeklyMeetings = weeklyMeetings;
    }
    public void setAttType(AttendanceType attType) {
        this.attType = attType;
    }
}