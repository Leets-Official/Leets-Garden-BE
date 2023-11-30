package com.example.leetsgarden.controller;

import com.example.leetsgarden.domain.WeeklyMeetings;
import com.example.leetsgarden.dto.request.AddWeeklyMeetingsRequest;
import com.example.leetsgarden.dto.response.UserAttendanceDetailsResponse;
import com.example.leetsgarden.dto.response.WeeklyMeetingsResponse;
import com.example.leetsgarden.service.WeeklyMeetingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meeting-weekly")
@RequiredArgsConstructor
public class WeeklyMeetingsApiController {

    private final WeeklyMeetingsService weeklyMeetingsService;
    @Operation(summary = "매주 모임승인페이지", description = "매주 진행하는 시간과 변동가능한 정보를 입력하면 출석페이지로 연동됩니다.", tags = {"WeeklyMeetingsApiController"})
    @PostMapping
    public ResponseEntity<WeeklyMeetingsResponse> save(@RequestBody AddWeeklyMeetingsRequest request) {
        WeeklyMeetings weeklyMeetings = weeklyMeetingsService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new WeeklyMeetingsResponse(weeklyMeetings));
    }

    @Operation(summary = "매주 진행되는 모임조회", description = "매주 진행하는 모임의 정보를 조회합니다. ", tags = {"WeeklyMeetingsApiController"})
    @GetMapping
    public ResponseEntity<List<WeeklyMeetingsResponse>> findAll(){
        List<WeeklyMeetingsResponse> weeklyMeetingsResponse = weeklyMeetingsService.findAll();
        return ResponseEntity.ok().body(weeklyMeetingsResponse);
    }

    @Operation(summary = "유저의 오늘 진행되는 모임조회", description = "유저의 오늘 진행하는 모임의 정보를 조회합니다. ", tags = {"WeeklyMeetingsApiController"})
    @GetMapping("/today")
    public ResponseEntity<List<WeeklyMeetingsResponse>> findUserTodayAll(Authentication authentication){
        List<WeeklyMeetingsResponse> weeklyMeetingsResponse = weeklyMeetingsService.findUserTodayAll(authentication.getName());
        return ResponseEntity.ok().body(weeklyMeetingsResponse);
    }

    @Operation(summary = "전체 잔디밭 조회", description = "동일한 모임을 기준으로 각각의 유저의 출석여부와 날짜를 조회합니다. ", tags = {"WeeklyMeetingsApiController"})
    @GetMapping("/all/{id}")
    public ResponseEntity<List<UserAttendanceDetailsResponse>> getAttendanceDetailsByMeetingId(@Parameter(name = "id", description = "meeting 의 id", in = ParameterIn.PATH)@PathVariable Long id) {
        List<UserAttendanceDetailsResponse> responseList = weeklyMeetingsService.getAttendanceDetailsByMeetingId(id);
        return ResponseEntity.ok(responseList);
    }

}
