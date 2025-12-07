package com.devtiro.tasks.repos;

import com.devtiro.tasks.domain.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository  extends JpaRepository<AppUser, UUID> {
    Optional<AppUser> findByUserName(String username);
}
