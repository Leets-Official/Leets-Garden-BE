package com.example.leetsgarden.service;

import com.example.leetsgarden.domain.*;
import com.example.leetsgarden.dto.request.AddWeeklyMeetingsRequest;
import com.example.leetsgarden.dto.response.AttendanceDetailsResponse;
import com.example.leetsgarden.dto.response.UserAttendanceDetailsResponse;
import com.example.leetsgarden.dto.response.WeeklyMeetingsResponse;
import com.example.leetsgarden.repository.AttendanceRepository;
import com.example.leetsgarden.repository.MeetingRepository;
import com.example.leetsgarden.repository.UserRepository;
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
    private final UserRepository userRepository;

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

    public List<WeeklyMeetingsResponse> findUserTodayAll(String userName) {
        User user = userRepository.findByUsername(userName).get();
        LocalDate now = LocalDate.now();
        List<WeeklyMeetings> weeklyMeetingsList = weeklyMeetingsRepository.findAllByMeetingDate(now);

        return weeklyMeetingsList.stream()
                .filter(s -> s.getMeeting().getUserMeetings()
                        .stream()
                        .anyMatch(userMeeting -> userMeeting.getUser().equals(user)))
                .map(WeeklyMeetingsResponse::new)
                .toList();
    }

    public List<UserAttendanceDetailsResponse> getAttendanceDetailsByMeetingId(Long meetingId) {
        List<Attendance> attendanceList = attendanceRepository.findByWeeklyMeetings_Meeting_Id(meetingId);

        return attendanceList.stream()
                .collect(Collectors.groupingBy(att -> att.getUser().getId(),
                     Collectors.mapping(AttendanceDetailsResponse::from, Collectors.toList())))
                .entrySet().stream()
                .map(entry -> new UserAttendanceDetailsResponse(entry.getKey(), userRepository.findById(entry.getKey()).get().getUsername(), entry.getValue()))
                .collect(Collectors.toList());
}
}
