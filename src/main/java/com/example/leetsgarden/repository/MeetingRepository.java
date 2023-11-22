package com.example.leetsgarden.repository;

import com.example.leetsgarden.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
