package sk.kirk.usergroupservice.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.kirk.usergroupservice.jpa.entity.GroupEntity;

/**
 * Репозиторий для работы с сущностями групп.
 * Предоставляет методы для поиска, сохранения, обновления и удаления пользователей,
 * а также другие специфические методы, необходимые для работы с пользователями.
 *
 * @author Kirillov Sergio
 */
@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {
}
