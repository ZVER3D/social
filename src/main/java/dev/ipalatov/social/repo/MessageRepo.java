package dev.ipalatov.social.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ipalatov.social.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
}