package com.example.leetsgarden.domain;

import com.example.leetsgarden.dto.request.AddMeetingRequest;
import com.example.leetsgarden.dto.request.UpdateMeetingRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String meetingName;

    @Column(nullable = false)
    private String meetingPlace;

    @Column
    private String meetingColor;

    @Column(nullable = false)
    private String meetingDay;

    public static Meeting from(AddMeetingRequest request) {
        return Meeting.builder()
                .meetingName(request.getMeetingName())
                .meetingPlace(request.getMeetingPlace())
                .meetingColor(request.getMeetingColor())
                .meetingDay(request.getMeetingDay())
                .build();
    }
    public void update(UpdateMeetingRequest request){
        this.meetingName = request.getMeetingName();
        this.meetingPlace = request.getMeetingPlace();
        this.meetingColor = request.getMeetingColor();
        this.meetingDay = request.getMeetingDay();
    }
}