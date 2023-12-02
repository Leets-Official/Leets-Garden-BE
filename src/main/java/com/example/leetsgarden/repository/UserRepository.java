package com.example.leetsgarden.repository;

import com.example.leetsgarden.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsUserByUsername(String username);
    Optional<User> findByName(String name);
    List<User> findAll();
}
