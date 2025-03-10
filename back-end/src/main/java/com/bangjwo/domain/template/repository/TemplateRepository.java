package com.bangjwo.domain.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bangjwo.domain.template.entity.Template;

public interface TemplateRepository extends JpaRepository<Template, Long> {
}
