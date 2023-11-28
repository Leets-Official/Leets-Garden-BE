package com.example.leetsgarden.service;

import com.example.leetsgarden.domain.*;
import com.example.leetsgarden.dto.request.AddWeeklyMeetingsRequest;
import com.example.leetsgarden.repository.AttendanceRepository;
import com.example.leetsgarden.repository.MeetingRepository;
import com.example.leetsgarden.repository.WeeklyMeetingsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeeklyMeetingsService {

    private final WeeklyMeetingsRepository weeklyMeetingsRepository;
    private final MeetingRepository meetingRepository;
    private final AttendanceRepository attendanceRepository;

    @Transactional
    public WeeklyMeetings save(AddWeeklyMeetingsRequest request) {
        Meeting savedMeeting = meetingRepository.findById(request.getMeetingId()).orElseThrow(() -> new IllegalArgumentException("모임이 존재하지 않습니다."));

        WeeklyMeetings weeklyMeetings = new WeeklyMeetings(request, savedMeeting);
        weeklyMeetingsRepository.save(weeklyMeetings);

        List<UserMeeting> userMeetings = savedMeeting.getUserMeetings();

        for (UserMeeting userMeeting : userMeetings) {
            Attendance attendance = new Attendance(userMeeting, weeklyMeetings);
            attendanceRepository.save(attendance);
        }
        return weeklyMeetings;
    }

    public List<WeeklyMeetings> findAll() {
        LocalDate now = LocalDate.now();
        List<WeeklyMeetings> weeklyMeetingsList = weeklyMeetingsRepository.findAll();

        return weeklyMeetingsList.stream()
                .filter(s -> s.getMeetingDate().isEqual(now) || s.getMeetingDate().isAfter(now))
                .collect(Collectors.toList());
    }
}
