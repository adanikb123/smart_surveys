package epolsoft.practice.smart_surveys.controller;

import epolsoft.practice.smart_surveys.dto.UserRequestDto;
import epolsoft.practice.smart_surveys.dto.UserResponseDto;
import epolsoft.practice.smart_surveys.entity.User;
import epolsoft.practice.smart_surveys.mapper.UserMapper;
import epolsoft.practice.smart_surveys.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/user")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Пользователи", description = "Все методы для работы с пользователями")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Operation(summary = "Изменение данных пользователя админом")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/update/{id}")
    public void updateUser(@Valid @RequestBody UserRequestDto userDto, @PathVariable Long id) {
        User user = userMapper.toEntity(userDto);
        userService.updateUser(user, id);
    }

    @Operation(summary = "Изменение пароля пользователем")
    @PreAuthorize("hasAnyAuthority('USER', 'MODER', 'ADMIN')")
    @PatchMapping(value = "/update_password")
    public void changePassword(@Size(min = 6, message = "Пароль должен быть больше 6 символов") @RequestBody String password) {
        userService.changePassword(password);
    }

    @Operation(summary = "Получить текущего пользователя")
    @PreAuthorize("hasAnyAuthority('USER', 'MODER', 'ADMIN')")
    @GetMapping("")
    public UserResponseDto getUser() {
        User user = userService.getCurrentUser();
        return userMapper.toResponseDto(user);
    }

    @Operation(summary = "Получение всех пользователей")
    @PreAuthorize("hasAnyAuthority('USER', 'MODER', 'ADMIN')")
    @GetMapping("/all")
    public Page<UserResponseDto> getAll(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.ASC) Pageable pageable) {
        Page<User> users = userService.getAllUsers(pageable);
        return users.map(userMapper::toResponseDto);
    }

    @Operation(summary = "Создать нового пользователя")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/new")
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto userDto) {
        return userMapper.toResponseDto(userService.createUser(userDto));
    }

}
