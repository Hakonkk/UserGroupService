package sk.kirk.usergroupservice.jpa.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kirk.usergroupservice.jpa.dto.UserDto;
import sk.kirk.usergroupservice.jpa.entity.UsersEntity;
import sk.kirk.usergroupservice.jpa.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер для работы с пользователями.
 * Предоставляет REST API для создания, обновления, получения и удаления пользователей,
 * а также для выполнения других операций, связанных с пользователями.
 *
 * @author Kirillov Sergio
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UsersController {

    private final UserService userService;

    /**
     * Получает список всех пользователей.
     *
     * @return Список DTO пользователей.
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        List<UsersEntity> users = userService.getAllUsers();
        return users.stream().map(userService::mapToUserDto).collect(Collectors.toList());
    }

    /**
     * Получает пользователя по ID.
     *
     * @param id ID пользователя.
     * @return ResponseEntity, содержащий DTO пользователя, или ResponseEntity со статусом 404, если пользователь не найден.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto userDto = userService.getUsersById(id);
        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Создает нового пользователя.
     *
     * @param userDto DTO пользователя для создания.
     * @return ResponseEntity, содержащий DTO созданного пользователя, со статусом 201 Created.
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UsersEntity createUser = userService.createUser(userDto);
        return new ResponseEntity<>(userService.mapToUserDto(createUser), HttpStatus.CREATED);
    }

    /**
     * Обновляет существующего пользователя по ID.
     *
     * @param id      ID пользователя для обновления.
     * @param userDto DTO пользователя с обновленными данными.
     * @return ResponseEntity, содержащий DTO обновленного пользователя, или ResponseEntity со статусом 404, если пользователь не найден.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable Integer id, @RequestBody UserDto userDto) {
        UserDto existingUserDto = userService.getUsersById(id);
        if (existingUserDto != null) {
            return ResponseEntity.notFound().build();
        }
        existingUserDto.setUsername(userDto.getUsername());
        existingUserDto.setGroupIds(userDto.getGroupIds());
        UsersEntity updateUser = userService.updateUser(existingUserDto);
        return new ResponseEntity<>(userService.mapToUserDto(updateUser), HttpStatus.OK);
    }

    /**
     * Удаляет пользователя по ID.
     *
     * @param id ID пользователя для удаления.
     * @return ResponseEntity со статусом 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Обработчик исключения EntityNotFoundException.
     * Возвращает ответ со статусом 404 Not Found и сообщением об ошибке.
     *
     * @param ex Исключение EntityNotFoundException, содержащее информацию об ошибке.
     * @return ResponseEntity со статусом 404 Not Found и сообщением об ошибке.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
