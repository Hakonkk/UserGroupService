package sk.kirk.usergroupservice.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.kirk.usergroupservice.jpa.entity.UsersEntity;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностями пользователей.
 * Предоставляет методы для поиска, сохранения, обновления и удаления пользователей,
 * а также другие специфические методы, необходимые для работы с пользователями.
 *
 * @author Kirillov Sergio
 */
@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Integer> {

    /**
     * Находит пользователя по имени пользователя.
     *
     * @param username Имя пользователя для поиска.
     * @return Optional, содержащий найденного пользователя, или пустой Optional, если пользователь не найден.
     */
    Optional<UsersEntity> findByUsername(String username);
}
