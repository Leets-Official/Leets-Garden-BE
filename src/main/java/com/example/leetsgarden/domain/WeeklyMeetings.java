package com.example.leetsgarden.domain;

import com.example.leetsgarden.dto.request.AddWeeklyMeetingsRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class WeeklyMeetings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate meetingDate;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    public WeeklyMeetings(AddWeeklyMeetingsRequest request, Meeting savedMeeting) {
        this.meetingDate = request.getMeetingDate();
        this.content = request.getContent();
        this.meeting = savedMeeting;
    }
}