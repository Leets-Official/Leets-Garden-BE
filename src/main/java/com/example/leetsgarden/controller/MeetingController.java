package com.example.leetsgarden.controller;

import com.example.leetsgarden.domain.Meeting;
import com.example.leetsgarden.dto.request.AddMeetingRequest;
import com.example.leetsgarden.dto.response.MeetingResponse;
import com.example.leetsgarden.service.MeetingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping
    public ResponseEntity<MeetingResponse> createMeeting(@Valid @RequestBody AddMeetingRequest request) {
        Meeting createdMeeting = meetingService.createMeeting(request);

        // 생성된 Meeting 정보로 응답 생성
        MeetingResponse meetingResponse = MeetingResponse.fromMeeting(createdMeeting);

        // 생성된 Meeting 정보와 함께 201 Created 응답 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(meetingResponse);
    }
}
