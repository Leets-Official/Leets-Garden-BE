package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.Attendance;
import com.example.leetsgarden.domain.Meeting;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class MeetingResponse {

    private Long id;
    private LocalDateTime dateTime;
    private String type;
    private String place;
    private String content;
    private String color;
    private List<Attendance> attendanceList;

    public static MeetingResponse from(Meeting meeting) {
        return MeetingResponse.builder()
                .id(meeting.getId())
                .dateTime(meeting.getDateTime())
                .type(meeting.getType())
                .place(meeting.getPlace())
                .content(meeting.getContent())
                .color(meeting.getColor())
                .attendanceList(meeting.getAttendanceList())
                .build();
    }
}
