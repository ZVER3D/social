package dev.ipalatov.social.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ipalatov.social.domain.User;

public interface UserDetailsRepo extends JpaRepository<User, String> {

}