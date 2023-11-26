package com.example.leetsgarden.repository;

import com.example.leetsgarden.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name);
    Long countUserByUsername(String userId);

}
