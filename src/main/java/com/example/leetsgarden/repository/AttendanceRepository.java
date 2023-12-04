package com.example.leetsgarden.repository;

import com.example.leetsgarden.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByWeeklyMeetings_Meeting_Id(Long meetingId);

    List<Attendance> findByWeeklyMeetings_Id(Long weeklyMeetings_id);
}
