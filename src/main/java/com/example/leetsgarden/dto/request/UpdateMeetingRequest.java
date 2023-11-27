package com.example.leetsgarden.dto.request;

import lombok.Getter;

@Getter
public class UpdateMeetingRequest {
    private String meetingName;

    private String meetingPlace;

    private String meetingDay;
}
