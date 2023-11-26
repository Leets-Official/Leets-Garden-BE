package com.example.leetsgarden.service;

import com.example.leetsgarden.domain.Authority;
import com.example.leetsgarden.domain.User;
import com.example.leetsgarden.dto.request.UserRequest;
import com.example.leetsgarden.dto.response.RegisterResponse;
import com.example.leetsgarden.dto.response.UserResponse;
import com.example.leetsgarden.repository.UserRepository;
import com.example.leetsgarden.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    @Transactional
    public ResponseEntity<UserResponse> login(UserRequest request) throws Exception {
        String messsage;
        if (userRepository.countUserByUserId(request.getUserid())==1){
            User user = userRepository.findByUserId(request.getUserid()).get();

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                messsage = "비밀 번호가 틀립니다.";

                UserResponse signResponse = UserResponse.builder()
                        .result(false)
                        .message(messsage)
                        .build();
                return new ResponseEntity<>(signResponse, HttpStatus.UNAUTHORIZED);
            }
            UserResponse signResponse= UserResponse.builder()
                    .id(user.getId())
                    .userId(user.getUserId())
                    .name(user.getName())
                    .major(user.getMajor())
                    .teamType(user.getTeamType())
                    .studentNumber(user.getStudentNumber())
                    .attendanceList(user.getAttendanceList())
                    .roles(user.getRoles())
                    .token(jwtProvider.createToken(user.getUserId(), user.getRoles()))
                    .result(true)
                    .message("로그인 성공")
                    .build();
            return new ResponseEntity<>(signResponse, HttpStatus.OK);
        }else{
            messsage = "계정이 존재하지 않습니다.";
            UserResponse signResponse=  UserResponse.builder()
                    .result(false)
                    .message(messsage)
                    .build();
            return new ResponseEntity<>(signResponse, HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<RegisterResponse> register(UserRequest request) throws Exception {
        try {
            User user = User.builder()
                    .userId(request.getUserid())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .name(request.getName())
                    .major(request.getMajor())
                    .teamType(request.getTeamType())
                    .studentNumber(request.getStudentNumber())
                    .build();
            user.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));
            if (userRepository.countUserByUserId(user.getUserId())==0){
                userRepository.save(user);
            }else{
                return new ResponseEntity<>(new RegisterResponse(false, "중복된 ID"), HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return new ResponseEntity<>(new RegisterResponse(true, "회원가입 성공"), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<UserResponse> getUser(String id) throws Exception {
        if (userRepository.countUserByUserId(id)==1){
            User user = userRepository.findByUserId(id).get();
            return new ResponseEntity<>(new UserResponse(user, true,"계정 조회 성공"), HttpStatus.OK);
        }else{
            UserResponse signResponse = UserResponse.builder()
                    .result(false)
                    .message("계정이 존재하지 않습니다.")
                    .build();
            return new ResponseEntity<>(signResponse, HttpStatus.NOT_FOUND);
        }
    }

}