package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.Attendance;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@Builder
public class AttendanceDetailsResponse {
    private LocalDate meetingDate;
    private String attendanceType;

    public static AttendanceDetailsResponse from(Attendance attendance) {
        return AttendanceDetailsResponse.builder()
                .meetingDate(attendance.getWeeklyMeetings().getMeetingDate())
                .attendanceType(attendance.getAttType().name())
                .build();
    }
}
