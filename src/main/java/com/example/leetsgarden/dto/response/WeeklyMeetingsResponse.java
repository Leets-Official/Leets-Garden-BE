package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.Attendance;
import com.example.leetsgarden.domain.WeeklyMeetings;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class WeeklyMeetingsResponse {

    private final MeetingResponse meetingResponse;
    private final Long id;
    private final LocalDate meetingDate;
    private final String content;
    private final List<UserAttendanceResponse> userAttendanceResponses;

    public WeeklyMeetingsResponse(WeeklyMeetings weeklyMeetings, List<Attendance> attendanceList){
        this.id = weeklyMeetings.getId();
        this.meetingDate = weeklyMeetings.getMeetingDate();
        this.content = weeklyMeetings.getContent();

        this.userAttendanceResponses = attendanceList.stream()
                .map(UserAttendanceResponse::new)
                .collect(Collectors.toList());

        this.meetingResponse = MeetingResponse.from(weeklyMeetings.getMeeting());
    }
}
