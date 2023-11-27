package com.example.leetsgarden.service;

import com.example.leetsgarden.domain.Meeting;
import com.example.leetsgarden.dto.request.AddMeetingRequest;
import com.example.leetsgarden.dto.request.UpdateMeetingRequest;
import com.example.leetsgarden.repository.MeetingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;

    public Meeting save(AddMeetingRequest request) {
        Meeting meeting = Meeting.from(request);
        return meetingRepository.save(meeting);
    }

    public Meeting findById(Long id) {
        return meetingRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public Meeting updateById(Long id, UpdateMeetingRequest request) {
        Meeting meeting = meetingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("모임이 존재하지 않습니다"));
        meeting.update(request);
        return meeting;
    }
}
