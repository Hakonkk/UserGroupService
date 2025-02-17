package sk.kirk.usergroupservice.jpa.dto;

import lombok.*;

/**
 * DTO (Data Transfer Object) для сущности "Группа".
 * Используется для передачи данных о группах между слоями приложения.
 *
 * @author Kirillov Sergio
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    /**
     * Уникальный идентификатор группы.
     */
    private Integer id;

    /**
     * Название группы.
     */
    private String name;
}
