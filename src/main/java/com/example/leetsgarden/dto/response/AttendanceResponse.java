package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.Attendance;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AttendanceResponse {
    private Long id;
    private String attendanceType;

    public static AttendanceResponse from(Attendance attendance) {
        return AttendanceResponse.builder()
                .id(attendance.getId())
                .attendanceType(attendance.getAttType().name())
                .build();
    }

}
