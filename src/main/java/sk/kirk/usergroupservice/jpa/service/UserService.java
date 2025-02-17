package sk.kirk.usergroupservice.jpa.service;

import org.springframework.stereotype.Service;
import sk.kirk.usergroupservice.jpa.dto.GroupDto;
import sk.kirk.usergroupservice.jpa.dto.UserDto;
import sk.kirk.usergroupservice.jpa.entity.UsersEntity;

import java.util.List;

/**
 * Сервис для управления пользователями.
 * Предоставляет методы для выполнения CRUD операций и других действий, связанных с пользователями.
 *
 * @author Kirillov Sergio
 */
@Service
public interface UserService {

    List<UsersEntity> getAllUsers();

    UserDto mapToUserDto(UsersEntity usersEntity);

    UserDto getUsersById(Integer id);

    UsersEntity createUser(UserDto userDto);

    UsersEntity updateUser(UserDto userDto);

    void deleteUser(Integer id);

    void addUserToGroup(Integer userId, List<Integer> groupIds);

    List<GroupDto> getUserGroups(String idOrName);
}
