package com.example.leetsgarden.controller;

import com.example.leetsgarden.domain.Template;
import com.example.leetsgarden.dto.request.AddTemplateRequest;
import com.example.leetsgarden.dto.response.TemplateResponse;
import com.example.leetsgarden.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/template")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping
    public ResponseEntity<TemplateResponse> save(@RequestBody AddTemplateRequest request) {
        Template savedTemplate = templateService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(TemplateResponse.from(savedTemplate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemplateResponse> findById(@PathVariable Long id) {
        Template template = templateService.findById(id);
        return ResponseEntity.ok().body(TemplateResponse.from(template));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TemplateResponse> update(@PathVariable Long id, @RequestBody AddTemplateRequest request) {
        Template updatedTemplate = templateService.update(id, request);
        return ResponseEntity.ok().body(TemplateResponse.from(updatedTemplate));
    }


}
