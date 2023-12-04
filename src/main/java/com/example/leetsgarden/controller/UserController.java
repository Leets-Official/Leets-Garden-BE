package com.example.leetsgarden.controller;

import com.example.leetsgarden.dto.request.AddUserRequest;
import com.example.leetsgarden.dto.request.LoginUserRequest;
import com.example.leetsgarden.dto.response.RegisterResponse;
import com.example.leetsgarden.dto.response.UserResponse;
import com.example.leetsgarden.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @Operation(summary = "로그인", description = "학번과 생일로 로그인가능합니다. ", tags = {"UserController"})
    @PostMapping(value = "/login")
    public ResponseEntity<UserResponse> signin(@RequestBody LoginUserRequest request) throws Exception {
        return userService.login(request);
    }

    @Operation(summary = "회원등록", description = "회원을 등록할 수 있습니다. ", tags = {"UserController"})
    @PostMapping(value = "/signup")
    public ResponseEntity<RegisterResponse> signup(@RequestBody AddUserRequest request) throws Exception {
        return userService.register(request);
    }
    @Operation(summary = "회원조회", description = "회원을 조회할 수 있습니다. ", tags = {"UserController"})
    @GetMapping("/users")
    public ResponseEntity<UserResponse> getUser(@RequestParam String username) throws Exception {
        return userService.getUser(username);
    }

}
