package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.Attendance;
import com.example.leetsgarden.domain.AttendanceType;
import lombok.Getter;

@Getter
public class UserAttendanceResponse {

    private final String name;
    private final Long attendanceId;
    private final AttendanceType attendanceType;
    private final String username;
    private final String fieldType;

    public UserAttendanceResponse(Attendance attendance) {
        this.name = attendance.getUser().getName();
        this.attendanceId = attendance.getId();
        this.attendanceType = attendance.getAttType();
        this.username = attendance.getUser().getUsername();
        this.fieldType = attendance.getUser().getFieldType();
    }
}
