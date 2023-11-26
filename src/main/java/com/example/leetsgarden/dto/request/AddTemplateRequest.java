package com.example.leetsgarden.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter

public class AddTemplateRequest {

    //private LocalDateTime dateTime;
    private String type;
    private String meetingName;
    private String place;
    private String content;
    private String color;
    private List<String> userNames;
}
