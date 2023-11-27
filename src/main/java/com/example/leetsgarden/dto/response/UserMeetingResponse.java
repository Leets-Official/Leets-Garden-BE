package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.UserMeeting;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserMeetingResponse {

    private final MeetingResponse meetingResponse;
    private final LocalDate meetingDate;
    private final String content;

    public UserMeetingResponse(UserMeeting userMeeting){
        this.meetingDate = userMeeting.getMeetingDate();
        this.content = userMeeting.getContent();
        this.meetingResponse = MeetingResponse.from(userMeeting.getMeeting());
    }
}
