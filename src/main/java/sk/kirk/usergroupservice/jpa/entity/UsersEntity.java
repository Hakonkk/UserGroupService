package sk.kirk.usergroupservice.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Сущность (Entity) "Пользователь".
 * Представляет собой запись о пользователе в базе данных.
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
@Table(name = "users")
public class UsersEntity {

    /**
     * Уникальный идентификатор пользователя.
     * Генерируется автоматически базой данных.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Имя пользователя.
     * Обязательное поле.
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * Набор групп, в которых состоит пользователь.
     * Связь "многие ко многим" с сущностью GroupEntity.
     * Загружается лениво (FetchType.LAZY).
     * Каскадирование операций MERGE (CascadeType.MERGE).
     * Таблица-связка: user_group.
     * Исключено из методов toString() и equals()/hashCode() для предотвращения рекурсивных вызовов.
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<GroupEntity> groups;
}
