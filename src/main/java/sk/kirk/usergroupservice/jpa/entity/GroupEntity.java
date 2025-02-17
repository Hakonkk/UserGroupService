package sk.kirk.usergroupservice.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Сущность (Entity) "Группа".
 * Представляет собой запись о группе в базе данных.
 *
 * @author Kirillov Sergio
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "groups")
public class GroupEntity {

    /**
     * Уникальный идентификатор группы.
     * Генерируется автоматически базой данных.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Название группы.
     * Обязательное поле.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Набор пользователей, входящих в эту группу.
     * Связь "многие ко многим" с сущностью UsersEntity.
     * Загружается лениво (FetchType.LAZY).
     * Исключено из методов toString() и equals()/hashCode() для предотвращения рекурсивных вызовов.
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groups")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<UsersEntity> users;
}

