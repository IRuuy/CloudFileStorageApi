package ru.shulgindaniil.cloudFileStorage.user.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "\"user\"")
public class User {
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
