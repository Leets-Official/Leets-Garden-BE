package com.example.leetsgarden.controller;

import com.example.leetsgarden.dto.request.AddAttendanceRequest;
import com.example.leetsgarden.dto.response.AttendanceResponse;
import com.example.leetsgarden.service.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "attendance", description = "출석 API")
@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;
    @Operation(summary = "출석체크", description = "출석 체크하여 출결 사항을 새로 업데이트합니다. ", tags = {"AttendanceController"})
    @PatchMapping("/{id}")
    public AttendanceResponse updateAttendance(@Parameter(name = "id", description = "attendance 의 id", in = ParameterIn.PATH)@PathVariable Long id,
                                               @RequestBody AddAttendanceRequest request) {
        return attendanceService.updateAttendance(id, request);
    }
    @Operation(summary = "출석체크조회", description = "출석 테이블을 기준으로 출석 정보를 확인합니다.", tags = {"AttendanceController"})
    @GetMapping("/meeting/{meetingId}")
    public ResponseEntity<List<AttendanceResponse>> getAttendancesByMeetingId(@Parameter(name = "id", description = "meeting 의 id", in = ParameterIn.PATH)@PathVariable Long meetingId) {
        List<AttendanceResponse> attendances = attendanceService.getAttendancesByMeetingId(meetingId);
        return ResponseEntity.ok().body(attendances);
    }
}
