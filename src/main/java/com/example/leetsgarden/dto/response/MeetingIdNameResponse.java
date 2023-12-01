package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.Meeting;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MeetingIdNameResponse {

    private Long id;

    private String name;

    public static MeetingIdNameResponse from(Meeting meeting) {
        return MeetingIdNameResponse.builder()
                .id(meeting.getId())
                .name(meeting.getName())
                .build();
    }
}
