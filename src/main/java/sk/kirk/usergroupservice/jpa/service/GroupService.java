package sk.kirk.usergroupservice.jpa.service;

import org.springframework.stereotype.Service;
import sk.kirk.usergroupservice.jpa.dto.GroupDto;
import sk.kirk.usergroupservice.jpa.entity.GroupEntity;

import java.util.List;


/**
 * Сервис для управления группами пользователей.
 * Предоставляет методы для выполнения CRUD операций и других действий, связанных с группами.
 *
 * @author Kirillov Sergio
 */
@Service
public interface GroupService {
    List<GroupEntity> getAllGroups();

    GroupDto mapToGroupDto(GroupEntity groupEntity);

    GroupDto getGroupById(Integer id);

    GroupEntity createGroup(GroupDto groupDto);

    GroupEntity updateGroup(GroupDto groupDto);

    void deleteById(Integer id);
}
