package com.example.leetsgarden.repository;

import com.example.leetsgarden.domain.User;
import com.example.leetsgarden.domain.WeeklyMeetings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface WeeklyMeetingsRepository extends JpaRepository<WeeklyMeetings, Long> {
    List<WeeklyMeetings> findAllByMeetingDate(LocalDate today);
}
