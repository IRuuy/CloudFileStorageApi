package ru.shulgindaniil.cloudFileStorage.user.web.mapper;

import org.mapstruct.Mapper;
import ru.shulgindaniil.cloudFileStorage.common.Mappable;
import ru.shulgindaniil.cloudFileStorage.user.domain.entity.User;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.UserDTO;

@Mapper(componentModel = "spring", uses = RoleDTOMapper.class)
public interface UserDTOMapper extends Mappable<User, UserDTO> {}
