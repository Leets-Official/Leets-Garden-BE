package com.example.leetsgarden.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class AddMeetingRequest {

    private String meetingName;

    private String meetingPlace;

    private String meetingDay;

    private List<String> userList;

}
