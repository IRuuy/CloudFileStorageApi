package ru.shulgindaniil.cloudFileStorage.user.web.mapper;

import org.mapstruct.Mapper;
import ru.shulgindaniil.cloudFileStorage.common.Mappable;
import ru.shulgindaniil.cloudFileStorage.user.domain.entity.User;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.UserMiniDTO;

@Mapper(componentModel = "spring")
public interface UserMiniDTOMapper extends Mappable<User, UserMiniDTO> {}
