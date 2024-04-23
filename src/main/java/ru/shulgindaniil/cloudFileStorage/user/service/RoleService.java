package ru.shulgindaniil.cloudFileStorage.user.service;

import ru.shulgindaniil.cloudFileStorage.user.web.dto.RoleDTO;

public interface RoleService {
    RoleDTO getUserRole();
    RoleDTO getAdminRole();
}
