package ru.shulgindaniil.cloudFileStorage.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.shulgindaniil.cloudFileStorage.security.utils.UserDetailsFactory;
import ru.shulgindaniil.cloudFileStorage.user.service.UserService;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.UserDto;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userService.getByEmail(username);
        return UserDetailsFactory.create(userDto);
    }
}
