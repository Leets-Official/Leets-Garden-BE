package com.example.leetsgarden.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AddMeetingRequest {

    private LocalDateTime dateTime;
    private String type;
    private String place;
    private String content;
    private String color;
    private List<String> userNames;
}
