package sk.kirk.usergroupservice.jpa.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * DTO (Data Transfer Object) для сущности "Пользователь".
 * Используется для передачи данных о пользователях между слоями приложения.
 *
 * @author Kirillov Sergio
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    /**
     * Уникальный идентификатор пользователя.
     */
    private Integer id;

    /**
     * Имя пользователя.
     */
    private String username;

    /**
     * Набор идентификаторов групп, в которых состоит пользователь.
     */
    private Set<Integer> groupIds;
}
