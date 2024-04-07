package ru.shulgindaniil.cloudFileStorage.security.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.RoleDto;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.UserDto;

import java.util.List;
import java.util.Set;

public class UserDetailsFactory {
    public static UserDetails create(UserDto userDto) {
        return new User(
                userDto.getEmail(),
                userDto.getPassword(),
                mapToGrantedAuthorities(userDto.getRoles())
        );
    }

    private static List<? extends GrantedAuthority> mapToGrantedAuthorities(Set<RoleDto> roles) {
        return roles.stream()
                    .map(RoleDto::getName)
                    .map(SimpleGrantedAuthority::new)
                    .toList();
    }
}
