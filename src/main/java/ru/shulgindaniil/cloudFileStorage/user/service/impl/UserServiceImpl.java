package ru.shulgindaniil.cloudFileStorage.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.shulgindaniil.cloudFileStorage.user.domain.entity.User;
import ru.shulgindaniil.cloudFileStorage.user.domain.exception.UserAlreadyExistException;
import ru.shulgindaniil.cloudFileStorage.user.domain.exception.UserNotFoundException;
import ru.shulgindaniil.cloudFileStorage.user.repository.UserRepository;
import ru.shulgindaniil.cloudFileStorage.user.service.RoleService;
import ru.shulgindaniil.cloudFileStorage.user.service.UserService;
import ru.shulgindaniil.cloudFileStorage.common.util.UniqueIdGenerator;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.UserDto;
import ru.shulgindaniil.cloudFileStorage.user.web.mapper.UserMapper;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UniqueIdGenerator<User> uniqueIdGenerator;

    @Value("${app.countOfBytesToGenerateUserId}")
    private Integer countBytesForUserId;

    @Override
    public UserDto getByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(
                String.format("User with email - '%s' not found.", email)
        ));

        return userMapper.toDto(user);
    }

    @Override
    public UserDto create(UserDto userDto) {
        userRepository.findByEmail(userDto.getEmail()).ifPresent(user -> {
            throw new UserAlreadyExistException(
                    String.format("User with email - %s already exist!", user.getEmail())
            );
        });

        userDto.setRoles(Set.of(roleService.getUserRole()));
        User user = userMapper.toEntity(userDto);

        user.setId(uniqueIdGenerator.generate(countBytesForUserId));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        user = userRepository.save(user);
        return userMapper.toDto(user);
    }
}
