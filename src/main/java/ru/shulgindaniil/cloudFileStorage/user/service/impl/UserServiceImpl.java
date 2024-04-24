package ru.shulgindaniil.cloudFileStorage.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.shulgindaniil.cloudFileStorage.security.PasswordNotEqualConfirmationPasswordException;
import ru.shulgindaniil.cloudFileStorage.user.domain.entity.User;
import ru.shulgindaniil.cloudFileStorage.user.domain.exception.UserAlreadyExistException;
import ru.shulgindaniil.cloudFileStorage.user.domain.exception.UserNotFoundException;
import ru.shulgindaniil.cloudFileStorage.user.repository.UserRepository;
import ru.shulgindaniil.cloudFileStorage.user.service.RoleService;
import ru.shulgindaniil.cloudFileStorage.user.service.UserService;
import ru.shulgindaniil.cloudFileStorage.common.util.UniqueIdGenerator;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.UserDTO;
import ru.shulgindaniil.cloudFileStorage.user.web.mapper.UserDTOMapper;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserDTOMapper userDTOMapper;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;
    private final UniqueIdGenerator<User> uniqueIdGenerator;

    @Value("${app.countBytesToGenerateUserId}")
    private Integer countBytesForUserId;

    @Override
    public UserDTO getByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(
                String.format("User with email - '%s' not found.", email)
        ));

        return userDTOMapper.toTarget(user);
    }

    @Override
    public UserDTO create(UserDTO userDto) {
        passwordValidation(userDto.getPassword(), userDto.getConfirmPassword());

        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new UserAlreadyExistException(
                    String.format("User with email - %s already exists!", userDto.getEmail())
            );
        }

        userDto.setRoles(Set.of(roleService.getUserRole()));
        User user = userDTOMapper.fromTarget(userDto);
        user.setId(uniqueIdGenerator.generate(userRepository, countBytesForUserId));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        user = userRepository.save(user);
        return userDTOMapper.toTarget(user);
    }

    private void passwordValidation(String password, String confirmPassword) {
        if(!password.equals(confirmPassword))
            throw new PasswordNotEqualConfirmationPasswordException();
    }
}
