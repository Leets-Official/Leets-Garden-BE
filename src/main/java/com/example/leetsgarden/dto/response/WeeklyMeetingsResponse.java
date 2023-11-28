package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.WeeklyMeetings;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class WeeklyMeetingsResponse {

    private final MeetingResponse meetingResponse;
    private final Long id;
    private final LocalDate meetingDate;
    private final String content;

    public WeeklyMeetingsResponse(WeeklyMeetings weeklyMeetings){
        this.id = weeklyMeetings.getId();
        this.meetingDate = weeklyMeetings.getMeetingDate();
        this.content = weeklyMeetings.getContent();
        this.meetingResponse = MeetingResponse.from(weeklyMeetings.getMeeting());
    }
}
