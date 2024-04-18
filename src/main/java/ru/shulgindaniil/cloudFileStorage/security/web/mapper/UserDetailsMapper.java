package ru.shulgindaniil.cloudFileStorage.security.web.mapper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.shulgindaniil.cloudFileStorage.security.web.dto.UserDetailsImpl;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.RoleDto;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.UserDto;

import java.util.List;
import java.util.Set;

public class UserDetailsMapper {
    public static UserDetails toUserDetails(UserDto userDto) {
        return UserDetailsImpl.builder()
                .id(userDto.getId())
                .username(userDto.getEmail())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .authorities(mapToGrantedAuthorities(userDto.getRoles()))
                .build();
    }

    private static List<? extends GrantedAuthority> mapToGrantedAuthorities(Set<RoleDto> roles) {
        return roles.stream()
                    .map(RoleDto::getName)
                    .map(SimpleGrantedAuthority::new)
                    .toList();
    }
}
