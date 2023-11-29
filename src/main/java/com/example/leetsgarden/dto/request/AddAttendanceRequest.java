package com.example.leetsgarden.dto.request;

import com.example.leetsgarden.domain.AttendanceType;
import lombok.Getter;

@Getter
public class AddAttendanceRequest {
    private AttendanceType newAttendanceType;
}
