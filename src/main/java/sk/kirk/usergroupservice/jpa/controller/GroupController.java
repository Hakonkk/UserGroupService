package sk.kirk.usergroupservice.jpa.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kirk.usergroupservice.jpa.dto.GroupDto;
import sk.kirk.usergroupservice.jpa.entity.GroupEntity;
import sk.kirk.usergroupservice.jpa.service.GroupService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер для работы с группами.
 * Предоставляет REST API для создания, обновления, получения и удаления пользователей,
 * а также для выполнения других операций, связанных с пользователями.
 *
 * @author Kirillov Sergio
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/groups")
public class GroupController {

    private final GroupService groupService;

    /**
     * Получает список всех групп.
     *
     * @return Список DTO групп.
     */
    @GetMapping
    public List<GroupDto> getAllGroups() {
        List<GroupEntity> groups = groupService.getAllGroups();
        return groups.stream()
                .map(groupService::mapToGroupDto)
                .collect(Collectors.toList());
    }

    /**
     * Получает группу по ID.
     *
     * @param id ID группы.
     * @return ResponseEntity, содержащий DTO группы, или ResponseEntity со статусом 404, если группа не найдена.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Integer id) {
        GroupDto groupDto = groupService.getGroupById(id);
        if (groupDto != null) {
            return ResponseEntity.ok(groupDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Создает новую группу.
     *
     * @param groupDto DTO группы для создания.
     * @return ResponseEntity, содержащий DTO созданной группы, со статусом 201 Created.
     */
    @PostMapping
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
        GroupEntity createGroup = groupService.createGroup(groupDto);
        return new ResponseEntity<>(groupService.mapToGroupDto(createGroup), HttpStatus.CREATED);
    }

    /**
     * Обновляет существующую группу.
     *
     * @param id       ID группы для обновления.
     * @param groupDto DTO группы с обновленными данными.
     * @return ResponseEntity, содержащий DTO обновленной группы.
     */
    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> updateGroupById(@PathVariable Integer id, @RequestBody GroupDto groupDto) {
        groupDto.setId(id);
        GroupEntity updateGroup = groupService.updateGroup(groupDto);
        return new ResponseEntity<>(groupService.mapToGroupDto(updateGroup), HttpStatus.OK);
    }

    /**
     * Удаляет группу по ID.
     *
     * @param id ID группы для удаления.
     * @return ResponseEntity со статусом 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<GroupDto> deleteGroupById(@PathVariable Integer id) {
        groupService.deleteById(id);
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
