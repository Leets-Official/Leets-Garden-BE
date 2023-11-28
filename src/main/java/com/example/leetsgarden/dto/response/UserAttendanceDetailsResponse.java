package com.example.leetsgarden.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserAttendanceDetailsResponse {
    private Long userId;
    private List<AttendanceDetailsResponse> attendanceDetailsList;

    public UserAttendanceDetailsResponse(Long userId, List<AttendanceDetailsResponse> attendanceDetailsList) {
        this.userId = userId;
        this.attendanceDetailsList = attendanceDetailsList;
    }
}
