package com.example.leetsgarden.service;

import com.example.leetsgarden.domain.Attendance;

import com.example.leetsgarden.domain.Template;
import com.example.leetsgarden.domain.User;
import com.example.leetsgarden.dto.request.AddTemplateRequest;
import com.example.leetsgarden.repository.AttendanceRepository;
import com.example.leetsgarden.repository.TemplateRepository;
import com.example.leetsgarden.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;

    @Transactional
    public Template save(AddTemplateRequest request) {
        Template template = Template.from(request);

        if (request.getUserNames().isEmpty()) {
            throw new IllegalArgumentException("참석자가 존재하지 않습니다.");
        }

        for (String userName : request.getUserNames()) {
            User user = userRepository.findByName(userName).orElseThrow(() -> new IllegalArgumentException("해당 User는 존재하지 않습니다."));

            Attendance attendance = new Attendance(user, template );
            attendanceRepository.save(attendance);
        }

        return templateRepository.save(template);
    }

    @Transactional
    public Template update(Long id, AddTemplateRequest request) {
        Template existingTemplate = templateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 Template이 존재하지 않습니다."));

        //existingTemplate.setDateTime(request.getDateTime());
        existingTemplate.setType(request.getType());
        existingTemplate.setPlace(request.getPlace());
        existingTemplate.setContent(request.getContent());
        existingTemplate.setColor(request.getColor());

        // 참석자
        List<Attendance> updatedAttendanceList = new ArrayList<>();
        for (String userName : request.getUserNames()) {
            User user = userRepository.findByName(userName)
                    .orElseThrow(() -> new IllegalArgumentException("해당 User는 존재하지 않습니다."));

            Attendance attendance = new Attendance(user, existingTemplate);
            attendanceRepository.save(attendance);
            updatedAttendanceList.add(attendance);
        }
        existingTemplate.setAttendanceList(updatedAttendanceList);

        return templateRepository.save(existingTemplate);
    }

    public Template findById(Long id) {
        return templateRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
