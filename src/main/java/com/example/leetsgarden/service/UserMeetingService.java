package com.example.leetsgarden.service;

import com.example.leetsgarden.domain.*;
import com.example.leetsgarden.dto.request.AddUserMeetingRequest;
import com.example.leetsgarden.repository.AttendanceRepository;
import com.example.leetsgarden.repository.MeetingRepository;
import com.example.leetsgarden.repository.UserMeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMeetingService {

    private final UserMeetingRepository userMeetingRepository;
    private final MeetingRepository meetingRepository;
    private final AttendanceRepository attendanceRepository;

    public UserMeeting save(Long meetingId, AddUserMeetingRequest request) {
        Meeting savedMeeting = meetingRepository.findById(meetingId).orElseThrow(() -> new IllegalArgumentException("모임이 존재하지 않습니다."));

        UserMeeting userMeeting = new UserMeeting(request, savedMeeting);
        userMeetingRepository.save(userMeeting);

        List<User> userList = savedMeeting.getUserList();

        for (User user : userList) {
            Attendance attendance = new Attendance(user, userMeeting);
            attendanceRepository.save(attendance);
        }
        return userMeeting;
    }
}
