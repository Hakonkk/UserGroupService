package sk.kirk.usergroupservice.jpa.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sk.kirk.usergroupservice.jpa.dto.GroupDto;
import sk.kirk.usergroupservice.jpa.entity.GroupEntity;
import sk.kirk.usergroupservice.jpa.mapping.GroupMapper;
import sk.kirk.usergroupservice.jpa.repository.GroupRepository;

import java.util.List;

/**
 * Реализация интерфейса GroupService.
 * Предоставляет методы для работы с группами, включая получение, создание, обновление и удаление.
 *
 * @author Kirillov Sergio
 */
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final GroupMapper groupMapper;

    /**
     * Получает список всех групп.
     *
     * @return Список сущностей GroupEntity.
     */
    @Override
    public List<GroupEntity> getAllGroups() {
        return groupRepository.findAll();
    }

    /**
     * Преобразует сущность GroupEntity в DTO GroupDto.
     *
     * @param groupEntity Сущность GroupEntity.
     * @return DTO GroupDto.
     */
    @Override
    public GroupDto mapToGroupDto(GroupEntity groupEntity) {
        return groupMapper.mapToDto(groupEntity);
    }

    /**
     * Получает группу по ID.
     *
     * @param id ID группы.
     * @return DTO GroupDto, или null, если группа не найдена.
     */
    @Override
    public GroupDto getGroupById(Integer id) {
        return groupRepository.findById(id)
                .map(groupMapper::mapToDto)
                .orElse(null);
    }

    /**
     * Создает новую группу.
     *
     * @param groupDto DTO группы для создания.
     * @return Созданная сущность GroupEntity.
     */
    @Override
    public GroupEntity createGroup(GroupDto groupDto) {
        GroupEntity groupEntity = groupMapper.mapToEntity(groupDto);
        return groupRepository.save(groupEntity);
    }

    /**
     * Обновляет существующую группу.
     *
     * @param groupDto DTO группы с обновленными данными.
     * @return Обновленная сущность GroupEntity.
     * @throws EntityNotFoundException Если группа с указанным ID не найдена.
     */
    @Override
    public GroupEntity updateGroup(GroupDto groupDto) {
        GroupEntity groupEntity = groupRepository.findById(groupDto.getId()).orElseThrow(
                () -> new EntityNotFoundException("Group not found"));
        groupEntity.setName(groupDto.getName());
        return groupRepository.save(groupEntity);
    }

    /**
     * Удаляет группу по ID.
     *
     * @param id ID группы для удаления.
     */
    @Override
    public void deleteById(Integer id) {
        groupRepository.deleteById(id);
    }
}
