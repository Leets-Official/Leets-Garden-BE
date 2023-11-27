package com.example.leetsgarden.repository;

import com.example.leetsgarden.domain.UserMeeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMeetingRepository extends JpaRepository<UserMeeting, Long> {
}
