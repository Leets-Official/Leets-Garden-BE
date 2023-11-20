package com.example.leetsgarden.repository;

import com.example.leetsgarden.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
