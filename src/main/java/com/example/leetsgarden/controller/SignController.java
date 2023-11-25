package com.example.leetsgarden.controller;

import com.example.leetsgarden.dto.request.SignRequest;
import com.example.leetsgarden.dto.response.RegisterResponse;
import com.example.leetsgarden.dto.response.SignResponse;
import com.example.leetsgarden.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignController {
    private final SignService userService;
    @PostMapping(value = "/login")
    public ResponseEntity<SignResponse> signin(@RequestBody SignRequest request) throws Exception {
//        return  new ResponseEntity<>(new SignResponse(), HttpStatus.OK);
        return userService.login(request);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResponse> signup(@RequestBody SignRequest request) throws Exception {
        return  new ResponseEntity<>(new RegisterResponse(), HttpStatus.OK);
//        return userService.register(request);
    }

    @GetMapping("/user/get")
    public ResponseEntity<SignResponse> getUser(@RequestParam String id) throws Exception {
        return  new ResponseEntity<>(new SignResponse(), HttpStatus.OK);
//        return userService.getUser(id);
    }

    @GetMapping("/admin/get")
    public ResponseEntity<SignResponse> getUserForAdmin(@RequestParam String id) throws Exception {
        return  new ResponseEntity<>(new SignResponse(), HttpStatus.OK);
//        return userService.getUser(id);
    }
}
