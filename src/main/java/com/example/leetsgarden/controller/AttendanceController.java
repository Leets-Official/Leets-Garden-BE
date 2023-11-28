package com.example.leetsgarden.controller;

import com.example.leetsgarden.dto.request.AddAttendanceRequest;
import com.example.leetsgarden.dto.response.AttendanceResponse;
import com.example.leetsgarden.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PatchMapping("/{id}")
    public AttendanceResponse updateAttendance(@PathVariable Long id,
                                               @RequestBody AddAttendanceRequest request) {
        return attendanceService.updateAttendance(id, request);
    }

    @GetMapping("/meeting/{meetingId}")
    public ResponseEntity<List<AttendanceResponse>> getAttendancesByMeetingId(@PathVariable Long meetingId) {
        List<AttendanceResponse> attendances = attendanceService.getAttendancesByMeetingId(meetingId);
        return ResponseEntity.ok().body(attendances);
    }
}
