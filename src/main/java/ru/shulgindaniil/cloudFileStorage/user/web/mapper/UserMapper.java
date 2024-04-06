package ru.shulgindaniil.cloudFileStorage.user.web.mapper;

import org.mapstruct.Mapper;
import ru.shulgindaniil.cloudFileStorage.common.Mappable;
import ru.shulgindaniil.cloudFileStorage.user.domain.entity.User;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.UserDto;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper extends Mappable<User, UserDto> {}
