package com.example.leetsgarden.repository;

import com.example.leetsgarden.domain.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {
}
