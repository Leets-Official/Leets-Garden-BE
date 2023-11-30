package com.example.leetsgarden.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserAttendanceDetailsResponse {
    private Long userId;
    private String username;
    private List<AttendanceDetailsResponse> attendanceDetailsList;

    public UserAttendanceDetailsResponse(Long userId, String username, List<AttendanceDetailsResponse> attendanceDetailsList) {
        this.userId = userId;
        this.username = username;
        this.attendanceDetailsList = attendanceDetailsList;
    }
}
