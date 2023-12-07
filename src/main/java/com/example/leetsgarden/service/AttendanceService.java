package com.example.leetsgarden.service;

import com.example.leetsgarden.domain.Attendance;
import com.example.leetsgarden.domain.AttendanceType;
import com.example.leetsgarden.dto.request.AddAttendanceRequest;
import com.example.leetsgarden.dto.response.AttendanceResponse;
import com.example.leetsgarden.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    @Transactional
    public AttendanceResponse updateAttendance(Long attendanceId, AddAttendanceRequest request) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 출석기록이 없습니다 : " + attendanceId));

        try {

            AttendanceType newAttendanceType = AttendanceType.valueOf(request.getNewAttendanceType().name());
            attendance.setAttType(newAttendanceType);
        } catch (IllegalArgumentException e) {

            throw new IllegalArgumentException("사용할 수 없는 출석 유형입니다: " + request.getNewAttendanceType());
        }

        Attendance updatedAttendance = attendanceRepository.save(attendance);

        return AttendanceResponse.builder()
                .id(updatedAttendance.getId())
                .attendanceType(updatedAttendance.getAttType().name())
                .build();
    }

    public List<AttendanceResponse> getAttendancesByMeetingId(Long meetingId) {
        List<Attendance> attendances = attendanceRepository.findByWeeklyMeetings_Meeting_Id(meetingId);
        return attendances.stream().map(AttendanceResponse::from).toList();
    }

    public List<Attendance> findByMeetingId(Long meetingId){
        return attendanceRepository.findByWeeklyMeetings_Meeting_Id(meetingId);
    }
}
