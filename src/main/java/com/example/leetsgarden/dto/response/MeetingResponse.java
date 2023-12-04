package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.Meeting;
import com.example.leetsgarden.domain.UserMeeting;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class MeetingResponse {

    private Long id;

    private String meetingName;

    private String meetingPlace;

    private String meetingDay;

    private List<String> userList;

    public static MeetingResponse from(Meeting meeting) {
        MeetingResponse meetingResponse = MeetingResponse.builder()
                .id(meeting.getId())
                .meetingName(meeting.getName())
                .meetingPlace(meeting.getPlace())
                .meetingDay(meeting.getMeetingDay())
                .build();

        List<UserMeeting> userMeetings = meeting.getUserMeetings();

        meetingResponse.userList = userMeetings.stream()
                .map(userMeeting -> userMeeting.getUser().getName())
                .collect(Collectors.toList());

        return meetingResponse;
    }
}