package ru.shulgindaniil.cloudFileStorage.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shulgindaniil.cloudFileStorage.user.domain.entity.Role;
import ru.shulgindaniil.cloudFileStorage.user.repository.RoleRepository;
import ru.shulgindaniil.cloudFileStorage.user.service.RoleService;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.RoleDTO;
import ru.shulgindaniil.cloudFileStorage.user.web.mapper.RoleDTOMapper;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleDTOMapper roleDTOMapper;

    @Override
    public RoleDTO getUserRole() {
        Role roleUser = roleRepository.findByName("ROLE_USER").get();
        return roleDTOMapper.toTarget(roleUser);
    }

    @Override
    public RoleDTO getAdminRole() {
        Role roleUser = roleRepository.findByName("ROLE_ADMIN").get();
        return roleDTOMapper.toTarget(roleUser);
    }
}
