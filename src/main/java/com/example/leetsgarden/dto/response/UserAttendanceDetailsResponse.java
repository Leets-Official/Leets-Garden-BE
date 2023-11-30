package com.example.leetsgarden.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserAttendanceDetailsResponse {
    private Long userId;
    private String name;
    private List<AttendanceDetailsResponse> attendanceDetailsList;

    public UserAttendanceDetailsResponse(Long userId, String name, List<AttendanceDetailsResponse> attendanceDetailsList) {
        this.userId = userId;
        this.name = name;
        this.attendanceDetailsList = attendanceDetailsList;
    }
}
