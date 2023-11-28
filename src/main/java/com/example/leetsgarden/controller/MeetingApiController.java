package com.example.leetsgarden.controller;

import com.example.leetsgarden.domain.Meeting;
import com.example.leetsgarden.dto.request.AddMeetingRequest;
import com.example.leetsgarden.dto.request.UpdateMeetingRequest;
import com.example.leetsgarden.dto.response.MeetingResponse;
import com.example.leetsgarden.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meeting-info")
@RequiredArgsConstructor
public class MeetingApiController {

    private final MeetingService meetingService;

    @PostMapping
    public ResponseEntity<MeetingResponse> save(@RequestBody AddMeetingRequest request) {
        Meeting savedMeeting = meetingService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(MeetingResponse.from(savedMeeting));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingResponse> findById(@PathVariable Long id) {
        Meeting meeting = meetingService.findById(id);
        return ResponseEntity.ok().body(MeetingResponse.from(meeting));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MeetingResponse> update(@PathVariable Long id, @RequestBody UpdateMeetingRequest request) {
        Meeting updatedMeeting = meetingService.updateById(id, request);
        return ResponseEntity.ok().body(MeetingResponse.from(updatedMeeting));
    }
}
