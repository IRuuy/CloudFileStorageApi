package ru.shulgindaniil.cloudFileStorage.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.RoleDTO;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.UserDTO;

import java.util.List;
import java.util.Set;

public class UserDetailsMapper {
    public static UserDetails toUserDetails(UserDTO userDto) {
        return UserDetailsImpl.builder()
                .id(userDto.getId())
                .username(userDto.getEmail())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .authorities(mapToGrantedAuthorities(userDto.getRoles()))
                .build();
    }

    private static List<? extends GrantedAuthority> mapToGrantedAuthorities(Set<RoleDTO> roles) {
        return roles.stream()
                    .map(RoleDTO::getName)
                    .map(SimpleGrantedAuthority::new)
                    .toList();
    }
}
