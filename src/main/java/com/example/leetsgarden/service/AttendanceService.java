package com.example.leetsgarden.service;

import com.example.leetsgarden.domain.Attendance;
import com.example.leetsgarden.domain.Meeting;
import com.example.leetsgarden.domain.User;
import com.example.leetsgarden.repository.AttendanceRepository;
import com.example.leetsgarden.repository.MeetingRepository;
import com.example.leetsgarden.repository.TemplateRepository;
import com.example.leetsgarden.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final MeetingRepository meetingRepository;
    private final TemplateRepository templateRepository;

    @Autowired
    public AttendanceService(
            AttendanceRepository attendanceRepository,
            UserRepository userRepository,
            MeetingRepository meetingRepository,
            TemplateRepository templateRepository
    ) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
        this.meetingRepository = meetingRepository;
        this.templateRepository = templateRepository;
    }

    public void checkAttendance(Long meetingId) {
        User user = getUser();

        Meeting meeting = getMeeting(meetingId);

        // 출석 체크
        Attendance attendanceThisWeek = new Attendance(user, meeting.getTemplate(), false);
        attendanceRepository.save(attendanceThisWeek);
        attendanceThisWeek.checkAttendance();

        // 매주 다음 주 미팅 날짜 갱신
        meeting.setNextWeekMeetingDate(meeting.getNextWeekMeetingDate().plusWeeks(1));
    }

//여긴 나중에 추가 일단, 보류
    private User getUser() {

        return userRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("해당 User가 존재하지 않습니다."));
    }

    private Meeting getMeeting(Long meetingId) {
        return meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Meeting이 존재하지 않습니다."));
    }

}
