package com.example.leetsgarden.domain;
import com.example.leetsgarden.dto.request.AddMeetingRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
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
    private String type;

    @Column
    private String place;

    @Column
    private String content;

    @Column
    private String color;

    @OneToMany
    private List<Attendance> attendanceList;

    public static Meeting from(AddMeetingRequest request) {
        return Meeting.builder()
                .dateTime(request.getDateTime())
                .type(request.getType())
                .place(request.getPlace())
                .content(request.getContent())
                .color(request.getColor())
                .build();
    }
}