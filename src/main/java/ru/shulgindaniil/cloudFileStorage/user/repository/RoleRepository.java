package ru.shulgindaniil.cloudFileStorage.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.shulgindaniil.cloudFileStorage.user.domain.entity.Role;
import ru.shulgindaniil.cloudFileStorage.user.domain.entity.User;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
