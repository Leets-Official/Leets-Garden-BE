package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.Meeting;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MeetingResponse {
    private Long meetingId;
    private String meetingDay;
    private LocalDateTime thisWeekMeetingDate;
    private LocalDateTime nextWeekMeetingDate;
    private Long templateId;

    public static MeetingResponse fromMeeting(Meeting meeting) {
        return MeetingResponse.builder()
                .meetingId(meeting.getMeetingId())
                .meetingDay(meeting.getMeetingDay())
                .thisWeekMeetingDate(meeting.getThisWeekMeetingDate())
                .nextWeekMeetingDate(meeting.getNextWeekMeetingDate())
                .templateId(meeting.getTemplate().getId())
                .build();
    }
}
