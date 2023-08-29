package epolsoft.practice.smart_surveys.dto;

import epolsoft.practice.smart_surveys.entity.enums.RoleType;
import epolsoft.practice.smart_surveys.validators.EnumNamePattern;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "{username.notBlank}")
    @Size(min = 1, max = 50, message = "{username.wrongSize}")
    private String username;

    @NotBlank(message = "{email.notBlank}")
    @Size(min = 1, max = 30, message = "{email.wrongSize}")
    @Email(message = "{email.invalid}")
    private String email;

    @NotBlank(message = "{password.notBlank}")
    @Size(min = 6, message = "{password.wrongSize}")
    private String password;

    @EnumNamePattern(regexp = "USER|MODER|ADMIN")
    private RoleType role;
}
