package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.Attendance;
import com.example.leetsgarden.domain.Meeting;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MeetingAllResponse {

    private final MeetingResponse meetingResponse;

    private final List<UserAttendanceResponse> userAttendanceResponseList;

    public MeetingAllResponse(Meeting meeting, List<Attendance> attendanceList) {
        this.meetingResponse = MeetingResponse.from(meeting);
        this.userAttendanceResponseList = attendanceList.stream()
                .map(UserAttendanceResponse::new)
                .collect(Collectors.toList());
    }
}

