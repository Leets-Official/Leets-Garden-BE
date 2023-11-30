package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.Meeting;
import com.example.leetsgarden.domain.User;
import com.example.leetsgarden.domain.UserMeeting;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MeetingResponse {

    private Long id;

    private String meetingName;

    private String meetingPlace;

    private String meetingDay;

    private List<User> userList;

    public static MeetingResponse from(Meeting meeting) {
        MeetingResponse meetingResponse = MeetingResponse.builder()
                .id(meeting.getId())
                .meetingName(meeting.getName())
                .meetingPlace(meeting.getPlace())
                .meetingDay(meeting.getMeetingDay())
                .build();

        List<UserMeeting> userMeetings = meeting.getUserMeetings();

        meetingResponse.userList = userMeetings.stream()
                .map(UserMeeting::getUser)
                .toList();

        return meetingResponse;
    }
}