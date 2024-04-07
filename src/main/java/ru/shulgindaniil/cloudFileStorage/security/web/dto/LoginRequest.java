package ru.shulgindaniil.cloudFileStorage.security.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginRequest {
    @NotNull(message = "Email must be not null.")
    @Length(max=255, message = "Length email must be smaller than 255 symbols.")
    private String email;

    @NotNull(message = "Password must be not null.")
    @Length(max=255, message = "Length password must be smaller than 255 symbols.")
    private String password;
}
