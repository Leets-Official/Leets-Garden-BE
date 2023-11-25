package com.example.leetsgarden.service;

import com.example.leetsgarden.dto.request.SignRequest;
import com.example.leetsgarden.dto.response.RegisterResponse;
import com.example.leetsgarden.dto.response.SignResponse;
import com.example.leetsgarden.repository.UserRepository;
import com.example.leetsgarden.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    @Transactional
    public ResponseEntity<SignResponse> login(SignRequest request) throws Exception {
        return new ResponseEntity<>(new SignResponse(), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<RegisterResponse> register(SignRequest request) throws Exception {
        return new ResponseEntity<>(new RegisterResponse(), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<SignResponse> getUser(String id) throws Exception {
        return new ResponseEntity<>(new SignResponse(), HttpStatus.OK);
    }

}
