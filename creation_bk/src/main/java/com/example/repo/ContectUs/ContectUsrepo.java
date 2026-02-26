package com.example.repo.ContectUs;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Model.SupportMessage.ContectUs;

public interface ContectUsrepo extends JpaRepository<ContectUs, Long> {
	Optional<List<ContectUs>> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrSubjectContainingIgnoreCase(
            String name, String email, String subject);
}