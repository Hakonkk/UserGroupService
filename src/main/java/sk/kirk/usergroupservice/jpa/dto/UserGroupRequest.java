package sk.kirk.usergroupservice.jpa.dto;

import lombok.*;

import java.util.List;

/**
 * DTO (Data Transfer Object) для запроса на добавление пользователя в группы.
 * Используется для передачи данных о пользователе и группах, в которые его нужно добавить,
 * между слоями приложения.
 *
 * @author Kirillov Sergio
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupRequest {

    /**
     * Уникальный идентификатор пользователя.
     */
    private Integer userId;

    /**
     * Список уникальных идентификаторов групп, в которые нужно добавить пользователя.
     */
    private List<Integer> groupIds;
}
