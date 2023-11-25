package com.example.leetsgarden.repository;

import com.example.leetsgarden.domain.Meeting;
import com.example.leetsgarden.domain.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByMeetingDay(String meetingDay);

    List<Meeting> findByTemplateId(Long templateId);

}
