package com.example.leetsgarden.controller;

import com.example.leetsgarden.domain.Attendance;
import com.example.leetsgarden.domain.Meeting;
import com.example.leetsgarden.dto.request.AddMeetingRequest;
import com.example.leetsgarden.dto.request.UpdateMeetingRequest;
import com.example.leetsgarden.dto.response.AllUsersResponse;
import com.example.leetsgarden.dto.response.MeetingAllResponse;
import com.example.leetsgarden.dto.response.MeetingIdNameResponse;
import com.example.leetsgarden.dto.response.MeetingResponse;
import com.example.leetsgarden.service.AttendanceService;
import com.example.leetsgarden.service.MeetingService;
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
@RequestMapping("/meeting")
@RequiredArgsConstructor
public class MeetingApiController {

    private final MeetingService meetingService;
    private final AttendanceService attendanceService;

    @Operation(summary = "모임생성", description = "모임 이름과 장소 등의 세부정보를 입력하여 모임을 생성합니다. ", tags = {"MeetingApiController"})
    @PostMapping
    public ResponseEntity<MeetingResponse> save(@RequestBody AddMeetingRequest request) {
        Meeting savedMeeting = meetingService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(MeetingResponse.from(savedMeeting));
    }

    @Operation(summary = "생성된 모임 조회", description = "생성한 모임을 조회합니다.", tags = {"MeetingApiController"})
    @GetMapping("/{id}")
    public ResponseEntity<MeetingAllResponse> findById(@Parameter(name = "id", description = "meeting 의 id", in = ParameterIn.PATH) @PathVariable Long id) {
        Meeting meeting = meetingService.findById(id);
        List<Attendance> attendances = attendanceService.findByMeetingId(id);
        return ResponseEntity.ok().body(new MeetingAllResponse(meeting, attendances));
    }

    @Operation(summary = "유저의 모든 모임 조회", description = "유저의 모든 모임을 조회합니다.", tags = {"MeetingApiController"})
    @GetMapping("my")
    public ResponseEntity<List<MeetingResponse>> findByUserAll(Authentication authentication) {
        List<MeetingResponse> meetings = meetingService.findByUserAll(authentication.getName());
        return ResponseEntity.ok().body(meetings);
    }

    @Operation(summary = "모임수정", description = "모임의 세부정보를 수정할 수 있습니다. ", tags = {"MeetingApiController"})
    @PatchMapping("/{id}")
    public ResponseEntity<MeetingResponse> update(@Parameter(name = "id", description = "meeting 의 id", in = ParameterIn.PATH) @PathVariable Long id, @RequestBody UpdateMeetingRequest request) {
        Meeting updatedMeeting = meetingService.updateById(id, request);
        return ResponseEntity.ok().body(MeetingResponse.from(updatedMeeting));
    }

    @Operation(summary = "모든 모임 조회", description = "모든 모임을 조회합니다.", tags = {"MeetingApiController"})
    @GetMapping("/all")
    public ResponseEntity<List<MeetingIdNameResponse>> findAll(){
        return ResponseEntity.ok().body(meetingService.findAll());
    }

    @Operation(summary = "모든 유저 조회", description = "모든 유저의 ID와 이름을 조회합니다.", tags = {"MeetingApiController"})
    @GetMapping("/all-users")
    public ResponseEntity<List<AllUsersResponse>> findAllUsers() {
        List<AllUsersResponse> allUsers = meetingService.findAllUsers();
        return ResponseEntity.ok().body(allUsers);
    }

}
