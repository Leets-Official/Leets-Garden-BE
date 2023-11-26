package com.example.leetsgarden.controller;

import com.example.leetsgarden.domain.Meeting;
import com.example.leetsgarden.dto.request.AddMeetingRequest;
import com.example.leetsgarden.dto.response.MeetingResponse;
import com.example.leetsgarden.service.MeetingService;
import com.example.leetsgarden.service.TemplateService;
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
    private final TemplateService templateService;
    @Autowired
    public MeetingController(MeetingService meetingService, TemplateService templateService) {
        this.meetingService = meetingService;
        this.templateService = templateService;
    }
    @PostMapping
    public ResponseEntity<MeetingResponse> createMeeting(@Valid @RequestBody AddMeetingRequest request) {
        Meeting createdMeeting = meetingService.createMeeting(request);
        MeetingResponse meetingResponse = MeetingResponse.fromMeeting(createdMeeting);

        return ResponseEntity.status(HttpStatus.CREATED).body(meetingResponse);
    }
}
