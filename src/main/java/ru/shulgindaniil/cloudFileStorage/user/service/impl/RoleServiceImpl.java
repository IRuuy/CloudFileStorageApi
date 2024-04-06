package ru.shulgindaniil.cloudFileStorage.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shulgindaniil.cloudFileStorage.user.domain.entity.Role;
import ru.shulgindaniil.cloudFileStorage.user.repository.RoleRepository;
import ru.shulgindaniil.cloudFileStorage.user.service.RoleService;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.RoleDto;
import ru.shulgindaniil.cloudFileStorage.user.web.mapper.RoleMapper;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    @Override
    public RoleDto getUserRole() {
        Role roleUser = roleRepository.findByName("ROLE_USER").get();
        return roleMapper.toDto(roleUser);
    }
}
