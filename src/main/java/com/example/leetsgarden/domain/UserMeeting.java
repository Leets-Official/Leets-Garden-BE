package com.example.leetsgarden.domain;

import com.example.leetsgarden.dto.request.AddUserMeetingRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMeeting {

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

    public UserMeeting(AddUserMeetingRequest request, Meeting savedMeeting) {
        this.meetingDate = request.getMeetingDate();
        this.content = request.getContent();
        this.meeting = savedMeeting;
    }
}