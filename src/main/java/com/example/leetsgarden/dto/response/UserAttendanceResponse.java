package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.Attendance;
import com.example.leetsgarden.domain.AttendanceType;
import lombok.Getter;

@Getter
public class UserAttendanceResponse {

    private final String name;
    private final Long attendanceId;
    private final AttendanceType attendanceType;

    public UserAttendanceResponse(Attendance attendance) {
        this.name = attendance.getUser().getName();
        this.attendanceId = attendance.getId();
        this.attendanceType = attendance.getAttType();
    }
}
