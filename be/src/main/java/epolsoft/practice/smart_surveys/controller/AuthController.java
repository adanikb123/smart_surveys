package epolsoft.practice.smart_surveys.controller;

import epolsoft.practice.smart_surveys.dto.JwtResponseDto;
import epolsoft.practice.smart_surveys.dto.LoginRequestDto;
import epolsoft.practice.smart_surveys.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/auth")
@Tag(name = "Авторизация", description = "Все методы для работы с пользователями")
public class AuthController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Авторизация пользователя в систему")
    @PostMapping("/signin")
    public JwtResponseDto authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest) {
        return userService.authenticateUser(loginRequest);
    }
}
