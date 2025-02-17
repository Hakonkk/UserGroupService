package sk.kirk.usergroupservice.jpa.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sk.kirk.usergroupservice.jpa.dto.GroupDto;
import sk.kirk.usergroupservice.jpa.dto.UserDto;
import sk.kirk.usergroupservice.jpa.entity.GroupEntity;
import sk.kirk.usergroupservice.jpa.entity.UsersEntity;
import sk.kirk.usergroupservice.jpa.mapping.GroupMapper;
import sk.kirk.usergroupservice.jpa.mapping.UserMapper;
import sk.kirk.usergroupservice.jpa.repository.GroupRepository;
import sk.kirk.usergroupservice.jpa.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса UserService.
 * Предоставляет методы для работы с пользователями, включая получение, создание, обновление, удаление
 * и добавление пользователей в группы.
 *
 * @author Kirillov Sergio
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    /**
     * Получает список всех пользователей.
     *
     * @return Список сущностей UsersEntity.
     */
    @Override
    public List<UsersEntity> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Преобразует сущность UsersEntity в DTO UserDto.
     *
     * @param usersEntity Сущность UsersEntity.
     * @return DTO UserDto.
     */
    @Override
    public UserDto mapToUserDto(UsersEntity usersEntity) {
        return userMapper.mapToDto(usersEntity);
    }

    /**
     * Получает пользователя по ID.
     *
     * @param id ID пользователя.
     * @return DTO UserDto, или null, если пользователь не найден.
     */
    @Override
    public UserDto getUsersById(Integer id) {
        UsersEntity usersEntity = userRepository.findById(id).orElse(null);
        return userMapper.mapToDto(usersEntity);
    }

    /**
     * Создает нового пользователя.
     *
     * @param userDto DTO пользователя для создания.
     * @return Созданная сущность UsersEntity.
     * @throws EntityNotFoundException Если одна из указанных групп не найдена.
     */
    @Override
    public UsersEntity createUser(UserDto userDto) {
        UsersEntity usersEntity = userMapper.mapToEntityForCreate(userDto);
        Set<GroupEntity> groups = new HashSet<>();
        if (userDto.getGroupIds() != null) {
            for (Integer groupId : userDto.getGroupIds()) {
                Optional<GroupEntity> groupOptional = groupRepository.findById(groupId);

                if (groupOptional.isPresent()) {
                    GroupEntity existingGroup = groupOptional.get();
                    groups.add(existingGroup);
                } else {
                    throw new EntityNotFoundException("Group with ID" + groupId + " not found");
                }
            }
        }
        usersEntity.setGroups(groups);
        return userRepository.save(usersEntity);
    }

    /**
     * Обновляет существующего пользователя.
     *
     * @param userDto DTO пользователя с обновленными данными.
     * @return Обновленная сущность UsersEntity, или null, если пользователь не найден.
     */
    @Override
    public UsersEntity updateUser(UserDto userDto) {
        UsersEntity userEntity = userRepository.findById(userDto.getId()).orElse(null);
        if (userEntity != null) {
            userEntity.setUsername(userDto.getUsername());
            return userRepository.save(userEntity);
        }
        return null;
    }

    /**
     * Удаляет пользователя по ID.
     *
     * @param id ID пользователя для удаления.
     */
    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    /**
     * Добавляет пользователя в одну или несколько групп.
     *
     * @param userId   ID пользователя.
     * @param groupIds Список ID групп, в которые нужно добавить пользователя.
     * @throws EntityNotFoundException Если пользователь или одна из групп не найдены.
     */
    @Override
    @Transactional
    public void addUserToGroup(Integer userId, List<Integer> groupIds) {
        UsersEntity user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User with ID" + userId + " not found"));
        List<GroupEntity> groups = groupRepository.findAllById(groupIds);
        user.setGroups(new HashSet<>(groups));
        userRepository.save(user);
    }

    /**
     * Получает список групп, в которых состоит пользователь, по его ID или имени.
     *
     * @param idOrName ID или имя пользователя.
     * @return Список DTO групп, в которых состоит пользователь.
     * @throws EntityNotFoundException Если пользователь не найден.
     */
    @Override
    public List<GroupDto> getUserGroups(String idOrName) {
        UsersEntity usersEntity;
        try {
            int id = Integer.parseInt(idOrName);
            usersEntity = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not Found"));
        } catch (NumberFormatException e) {
            usersEntity = userRepository.findByUsername(idOrName).orElseThrow(() -> new EntityNotFoundException("User not found"));
        }
        return usersEntity.getGroups().stream()
                .map(groupMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
