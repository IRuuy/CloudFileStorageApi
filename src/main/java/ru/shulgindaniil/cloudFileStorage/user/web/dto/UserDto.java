package ru.shulgindaniil.cloudFileStorage.user.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.shulgindaniil.cloudFileStorage.user.domain.entity.Role;
import ru.shulgindaniil.cloudFileStorage.user.web.validation.OnCreate;
import ru.shulgindaniil.cloudFileStorage.user.web.validation.OnUpdate;

import java.util.Set;

@Data
@Builder
public class UserDto {
    @NotNull(message = "Name must be not null.", groups = {OnCreate.class, OnUpdate.class})
    @Length(max=255, message = "Length name must be smaller than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotNull(message = "Username must be not null.", groups = {OnCreate.class, OnUpdate.class})
    @Length(max=255, message = "Length username must be smaller than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<RoleDto> roles;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Confirm password must be not null.", groups = OnCreate.class)
    private String password;

    @NotNull(message = "Confirm password must be not null.", groups = OnCreate.class)
    @Length(max=255, message = "Length confirm password must be smaller than 255 symbols.", groups = OnCreate.class)
    private String confirmPassword;
}
