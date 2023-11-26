package com.example.leetsgarden.controller;

import com.example.leetsgarden.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 구조 확인을 위해 임시로 만든 페이지, 추후 한성민 코드로 수정 예정
@RestController
@RequestMapping("/attendances")

public class AttendanceController {
    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/{meetingId}/check-attendance")
    public ResponseEntity<String> checkAttendance(@PathVariable Long meetingId) {
        attendanceService.checkAttendance(meetingId);
        return ResponseEntity.ok("Attendance checked successfully.");
    }
}
