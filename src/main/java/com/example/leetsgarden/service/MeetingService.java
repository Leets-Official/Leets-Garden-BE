package com.example.leetsgarden.service;

import com.example.leetsgarden.domain.Attendance;
import com.example.leetsgarden.domain.Meeting;
import com.example.leetsgarden.domain.User;
import com.example.leetsgarden.dto.request.AddMeetingRequest;
import com.example.leetsgarden.repository.AttendanceRepository;
import com.example.leetsgarden.repository.MeetingRepository;
import com.example.leetsgarden.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;

    @Transactional
    public Meeting save(AddMeetingRequest request) {
        Meeting meeting = Meeting.from(request);

        if (!request.getUserNames().isEmpty()) {
            for (String userName : request.getUserNames()) {
                Optional<User> user = userRepository.findByName(userName);

                if (user.isPresent()) {
                    Attendance attendance = new Attendance(user.get(), meeting);
                    attendanceRepository.save(attendance);
                }
            }
        }
        return meetingRepository.save(meeting);
    }

    public Meeting findById(Long id) {
        return meetingRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
