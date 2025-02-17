package sk.kirk.usergroupservice.jpa.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kirk.usergroupservice.jpa.dto.GroupDto;
import sk.kirk.usergroupservice.jpa.dto.UserGroupRequest;
import sk.kirk.usergroupservice.jpa.service.UserService;

import java.util.List;

/**
 * Контроллер для работы с пользователями со связью с группами.
 * Предоставляет REST API для создания, обновления, получения и удаления пользователей,
 * а также для выполнения других операций, связанных с пользователями.
 *
 * @author Kirillov Sergio
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/user-groups")
public class UserGroupController {

    private final UserService userService;

    /**
     * Добавляет пользователя в одну или несколько групп.
     *
     * @param request Запрос, содержащий ID пользователя и список ID групп.
     * @return ResponseEntity со статусом 200 OK в случае успешного добавления.
     */
    @PostMapping()
    public ResponseEntity<Void> addUserToGroup(@RequestBody UserGroupRequest request) {
        userService.addUserToGroup(request.getUserId(), request.getGroupIds());
        return ResponseEntity.ok().build();
    }

    /**
     * Получает список групп, в которых состоит пользователь, по его ID или имени.
     *
     * @param idOrName ID или имя пользователя.
     * @return ResponseEntity, содержащий список DTO групп, в которых состоит пользователь,
     * или ResponseEntity со статусом 404, если пользователь не найден.
     */
    @GetMapping("/{idOrName}/groups")
    public ResponseEntity<List<GroupDto>> getUserGroups(@PathVariable("idOrName") String idOrName) {
        List<GroupDto> groupDtoList = userService.getUserGroups(idOrName);
        return ResponseEntity.ok().body(groupDtoList);
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
