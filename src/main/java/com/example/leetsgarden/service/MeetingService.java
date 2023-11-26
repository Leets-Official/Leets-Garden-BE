package com.example.leetsgarden.service;

import com.example.leetsgarden.domain.Meeting;
import com.example.leetsgarden.domain.Template;
import com.example.leetsgarden.service.TemplateService;
import com.example.leetsgarden.dto.request.AddMeetingRequest;
import com.example.leetsgarden.repository.MeetingRepository;
import com.example.leetsgarden.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;


@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final TemplateRepository templateRepository;
    private final TemplateService templateService;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository, TemplateRepository templateRepository,  TemplateService templateService) {
        this.meetingRepository = meetingRepository;
        this.templateRepository = templateRepository;
        this.templateService = templateService;

    }
    public Meeting createMeeting(AddMeetingRequest request) {

        String meetingDay = request.getMeetingDay();

        Template template = templateRepository.findById(request.getTemplateId())
                .orElseThrow(() -> new IllegalArgumentException("해당 Template이 존재하지 않습니다."));


        Meeting meeting = new Meeting();
        meeting.setMeetingDay(meetingDay);
        meeting.setTemplate(template);


        meeting.setMeetingDay(meetingDay);

        meeting = meetingRepository.save(meeting);

        return meeting;
    }

  /*
    @Scheduled(cron = "0 0 0 ? * MON") // 일단, 매주 월요일 자정에 실행으로 설정, 이날 모든 스터디 생성
    public void generateWeeklyMeeting() {
        List<Template> templates = templateRepository.findAll();

        for (Template template : templates) {
            generateWeeklyMeeting(template);
        }
    }

    private void generateWeeklyMeeting(Template template) {

        LocalDateTime now = LocalDateTime.now();
        DayOfWeek templateDayOfWeek = DayOfWeek.valueOf(template.getMeetingDay().toUpperCase());
        LocalDateTime nextWeekMeetingDate = now.with(TemporalAdjusters.next(templateDayOfWeek));


        Meeting meeting = new Meeting();
        meeting.setMeetingDay(template.getMeetingDay());
        meeting.setThisWeekMeetingDate(nextWeekMeetingDate);
        meeting.setNextWeekMeetingDate(nextWeekMeetingDate.plusWeeks(1));
        meeting.setTemplate(template);

        meetingRepository.save(meeting);


        generateAttendance(meeting);
    }
   */
}
