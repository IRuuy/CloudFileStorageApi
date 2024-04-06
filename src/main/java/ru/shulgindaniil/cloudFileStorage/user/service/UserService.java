package ru.shulgindaniil.cloudFileStorage.user.service;

import ru.shulgindaniil.cloudFileStorage.user.web.dto.UserDto;

public interface UserService {
    UserDto getByEmail(String email);
    UserDto create(UserDto userDto);
}
