package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.Meeting;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MeetingResponse {

    private Long id;

    private String meetingName;

    private String meetingPlace;

    private String meetingDay;

    public static MeetingResponse from(Meeting meeting) {
        return MeetingResponse.builder()
                .id(meeting.getId())
                .meetingName(meeting.getName())
                .meetingPlace(meeting.getPlace())
                .meetingDay(meeting.getMeetingDay())
                .build();
    }
}
