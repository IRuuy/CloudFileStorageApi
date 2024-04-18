package ru.shulgindaniil.cloudFileStorage.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.shulgindaniil.cloudFileStorage.security.web.mapper.UserDetailsMapper;
import ru.shulgindaniil.cloudFileStorage.user.service.UserService;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.UserDto;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userService.getByEmail(username);
        return UserDetailsMapper.toUserDetails(userDto);
    }
}
