package com.example.leetsgarden.service;

import com.example.leetsgarden.domain.Attendance;
import com.example.leetsgarden.domain.Meeting;
import com.example.leetsgarden.domain.UserMeeting;
import com.example.leetsgarden.domain.WeeklyMeetings;
import com.example.leetsgarden.dto.request.AddWeeklyMeetingsRequest;
import com.example.leetsgarden.dto.response.WeeklyMeetingsResponse;
import com.example.leetsgarden.repository.AttendanceRepository;
import com.example.leetsgarden.repository.MeetingRepository;
import com.example.leetsgarden.repository.WeeklyMeetingsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public List<WeeklyMeetingsResponse> findAll() {
        LocalDate now = LocalDate.now();
        List<WeeklyMeetings> weeklyMeetingsList = weeklyMeetingsRepository.findAll();

        return weeklyMeetingsList.stream()
                .filter(s -> !s.getMeetingDate().isBefore(now))
                .map(WeeklyMeetingsResponse::new)
                .toList();
    }
}
