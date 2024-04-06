package ru.shulgindaniil.cloudFileStorage.user.web.mapper;

import org.mapstruct.Mapper;
import ru.shulgindaniil.cloudFileStorage.common.Mappable;
import ru.shulgindaniil.cloudFileStorage.user.domain.entity.Role;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.RoleDto;

@Mapper(componentModel = "spring")
public interface RoleMapper extends Mappable<Role, RoleDto> {}