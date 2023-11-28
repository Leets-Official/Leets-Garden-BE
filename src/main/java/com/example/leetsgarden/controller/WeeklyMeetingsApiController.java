package com.example.leetsgarden.controller;

import com.example.leetsgarden.domain.WeeklyMeetings;
import com.example.leetsgarden.dto.request.AddWeeklyMeetingsRequest;
import com.example.leetsgarden.dto.response.WeeklyMeetingsResponse;
import com.example.leetsgarden.service.WeeklyMeetingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetings")
@RequiredArgsConstructor
public class WeeklyMeetingsApiController {

    private final WeeklyMeetingsService weeklyMeetingsService;

    @PostMapping
    public ResponseEntity<WeeklyMeetingsResponse> save(@RequestBody AddWeeklyMeetingsRequest request) {
        WeeklyMeetings weeklyMeetings = weeklyMeetingsService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new WeeklyMeetingsResponse(weeklyMeetings));
    }

    @GetMapping
    public ResponseEntity<List<WeeklyMeetingsResponse>> findAll(){
        List<WeeklyMeetingsResponse> weeklyMeetingsResponse = weeklyMeetingsService.findAll()
                .stream()
                .map(WeeklyMeetingsResponse::new)
                .toList();

        return ResponseEntity.ok().body(weeklyMeetingsResponse);
    }
}
