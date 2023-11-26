package com.example.leetsgarden.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class AddMeetingRequest {

    @NotBlank(message = "Meeting day must not be blank")
    private String meetingDay;
    private Long templateId;
}
