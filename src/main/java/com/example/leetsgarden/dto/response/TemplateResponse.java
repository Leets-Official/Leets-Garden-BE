package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.Attendance;
import com.example.leetsgarden.domain.Template;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class TemplateResponse {

    private Long id;
   // private LocalDateTime dateTime;
    private String type;
    private String place;
    private String meetingName;
    private String content;
    private String color;
    private List<Attendance> attendanceList;

    public static TemplateResponse from(Template template) {
        return TemplateResponse.builder()
                .id(template.getId())
                //.dateTime(template.getDateTime())
                .type(template.getType())
                .meetingName(template.getMeetingName())
                .place(template.getPlace())
                .content(template.getContent())
                .color(template.getColor())
                .attendanceList(template.getAttendanceList())
                .build();
    }

}
