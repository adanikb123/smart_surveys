package epolsoft.practice.smart_surveys.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @NotBlank(message = "{username.notBlank}")
    @Size(min = 1, max = 50, message = "{username.wrongSize}")
    private String username;

    @NotBlank(message = "{password.notBlank}")
    @Size(min = 6, message = "{password.wrongSize}")
    private String password;
}
