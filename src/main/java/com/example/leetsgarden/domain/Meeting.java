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
    private String name;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String meetingDay;

    public static Meeting from(AddMeetingRequest request) {
        return Meeting.builder()
                .name(request.getMeetingName())
                .place(request.getMeetingPlace())
                .meetingDay(request.getMeetingDay())
                .build();
    }
    public void update(UpdateMeetingRequest request){
        this.name = request.getMeetingName();
        this.place = request.getMeetingPlace();
        this.meetingDay = request.getMeetingDay();
    }
}