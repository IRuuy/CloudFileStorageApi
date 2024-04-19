package ru.shulgindaniil.cloudFileStorage.user.service;

import ru.shulgindaniil.cloudFileStorage.user.web.dto.RoleDto;

public interface RoleService {
    RoleDto getUserRole();
    RoleDto getAdminRole();
}
