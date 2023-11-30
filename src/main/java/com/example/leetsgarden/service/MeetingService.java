package com.example.leetsgarden.service;

import com.example.leetsgarden.domain.Meeting;
import com.example.leetsgarden.domain.User;
import com.example.leetsgarden.domain.WeeklyMeetings;
import com.example.leetsgarden.dto.request.AddMeetingRequest;
import com.example.leetsgarden.dto.request.UpdateMeetingRequest;
import com.example.leetsgarden.dto.response.MeetingResponse;
import com.example.leetsgarden.dto.response.WeeklyMeetingsResponse;
import com.example.leetsgarden.repository.MeetingRepository;
import com.example.leetsgarden.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;

    public Meeting save(AddMeetingRequest request) {
        List<String> users = request.getUserList();
        List<User> attendanceUserList = new ArrayList<>();

        for (String name : users) {
            User user = userRepository.findByName(name)
                    .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + name));

            attendanceUserList.add(user);
        }

        Meeting meeting = Meeting.from(request, attendanceUserList);
        return meetingRepository.save(meeting);
    }

    public Meeting findById(Long id) {
        return meetingRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<MeetingResponse> findByUserAll(String userName) {
        User user = userRepository.findByUsername(userName).get();
        List<Meeting> MeetingList = meetingRepository.findAll();
        return MeetingList.stream()
                .filter(s -> s.getUserMeetings()
                        .stream()
                        .anyMatch(userMeeting -> userMeeting.getUser().equals(user)))
                .map(meeting -> MeetingResponse.from(meeting))
                .toList();
    }

    @Transactional
    public Meeting updateById(Long id, UpdateMeetingRequest request) {
        Meeting meeting = meetingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("모임이 존재하지 않습니다"));
        meeting.update(request, userRepository);
        return meeting;
    }
}
