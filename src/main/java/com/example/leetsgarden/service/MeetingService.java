package com.example.leetsgarden.service;

import com.example.leetsgarden.domain.Meeting;
import com.example.leetsgarden.domain.Template;
import com.example.leetsgarden.dto.request.AddMeetingRequest;
import com.example.leetsgarden.repository.MeetingRepository;
import com.example.leetsgarden.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final TemplateRepository templateRepository;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository, TemplateRepository templateRepository) {
        this.meetingRepository = meetingRepository;
        this.templateRepository = templateRepository;
    }

    public Meeting createMeeting(AddMeetingRequest request) {
        Meeting meeting = new Meeting();
        meeting.setMeetingDay(request.getMeetingDay());

        // templateId에 해당하는 Template을 찾아서 연결
        Template template = templateRepository.findById(request.getTemplateId())
                .orElseThrow(() -> new IllegalArgumentException("해당 Template이 존재하지 않습니다."));
        meeting.setTemplate(template);

       return meetingRepository.save(meeting);
    }
}
