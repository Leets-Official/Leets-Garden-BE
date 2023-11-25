package com.example.leetsgarden;
import com.example.leetsgarden.domain.User;
import com.example.leetsgarden.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 임의의 User 추가
        User user1 = User.builder()
                .userId("user1")
                .password("password123")
                .name("김")
                .role("USER")
                .teamType("Development")
                .major("Computer Science")
                .studentNumber(123456)
                .build();

        User user2 = User.builder()
                .userId("user2")
                .password("password123")
                .name("서")
                .role("USER")
                .teamType("Development")
                .major("Computer Science")
                .studentNumber(123457)
                .build();

        User user3 = User.builder()
                .userId("user3")
                .password("password123")
                .name("현")
                .role("USER")
                .teamType("Development")
                .major("Computer Science")
                .studentNumber(123458)
                .build();

        userRepository.saveAll(List.of(user1, user2, user3));
    }
}
