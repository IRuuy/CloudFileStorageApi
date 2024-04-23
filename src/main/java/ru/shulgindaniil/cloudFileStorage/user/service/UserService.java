package ru.shulgindaniil.cloudFileStorage.user.service;

import ru.shulgindaniil.cloudFileStorage.user.web.dto.UserDTO;

public interface UserService {
    UserDTO getByEmail(String email);
    UserDTO create(UserDTO userDto);
}
