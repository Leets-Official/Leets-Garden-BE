package com.example.leetsgarden.domain;
import com.example.leetsgarden.dto.request.AddTemplateRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column
    // private LocalDateTime dateTime;

    @Column
    private String meetingName;

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


    public static Template from(AddTemplateRequest request) {
        return Template.builder()
                //.dateTime(request.getDateTime())
                .type(request.getType())
                .meetingName(request.getMeetingName())
                .place(request.getPlace())
                .content(request.getContent())
                .color(request.getColor())
                .build();
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }
}