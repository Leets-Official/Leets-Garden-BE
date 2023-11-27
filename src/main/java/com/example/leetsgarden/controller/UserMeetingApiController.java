package com.example.leetsgarden.controller;

import com.example.leetsgarden.domain.UserMeeting;
import com.example.leetsgarden.dto.request.AddUserMeetingRequest;
import com.example.leetsgarden.dto.response.UserMeetingResponse;
import com.example.leetsgarden.service.UserMeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meeting")
@RequiredArgsConstructor
public class UserMeetingApiController {

    private final UserMeetingService userMeetingService;

    @PostMapping("/{meetingId}")
    public ResponseEntity<UserMeetingResponse> save(@PathVariable Long meetingId, @RequestBody AddUserMeetingRequest request){
        UserMeeting userMeeting = userMeetingService.save(meetingId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserMeetingResponse(userMeeting));
    }
}
