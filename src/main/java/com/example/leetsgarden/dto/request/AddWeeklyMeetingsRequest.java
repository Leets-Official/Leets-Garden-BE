package com.example.leetsgarden.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AddWeeklyMeetingsRequest {

    private LocalDate meetingDate;
    private String content;
}
