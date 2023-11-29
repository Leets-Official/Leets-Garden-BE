package com.example.leetsgarden.controller;

import com.example.leetsgarden.dto.request.UserRequest;
import com.example.leetsgarden.dto.response.RegisterResponse;
import com.example.leetsgarden.dto.response.UserResponse;
import com.example.leetsgarden.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping(value = "/login")
    public ResponseEntity<UserResponse> signin(@RequestBody UserRequest request) throws Exception {
        return userService.login(request);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResponse> signup(@RequestBody UserRequest request) {
        try {
            return userService.register(request);
        } catch (Exception e) {
            return new ResponseEntity<>(new RegisterResponse(
                    false, "올바르지 않은 권한"), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponse> getUser(@RequestParam String username) throws Exception {
        return userService.getUser(username);
    }
}
