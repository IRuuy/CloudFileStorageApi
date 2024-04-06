package ru.shulgindaniil.cloudFileStorage.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shulgindaniil.cloudFileStorage.user.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
