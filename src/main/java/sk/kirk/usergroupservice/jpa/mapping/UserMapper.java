package sk.kirk.usergroupservice.jpa.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sk.kirk.usergroupservice.jpa.dto.UserDto;
import sk.kirk.usergroupservice.jpa.entity.UsersEntity;

/**
 * Mapper для преобразования между сущностью UsersEntity и DTO UserDto.
 * Использует библиотеку MapStruct для автоматического преобразования полей.
 *
 * @author Kirillov Sergio
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Преобразует сущность UsersEntity в DTO UserDto.
     *
     * @param entity Сущность UsersEntity.
     * @return DTO UserDto.
     */
    UserDto mapToDto(UsersEntity entity);

    /**
     * Преобразует DTO UserDto в сущность UsersEntity для создания нового пользователя.
     * Поле id игнорируется при преобразовании, так как оно генерируется автоматически.
     *
     * @param dto DTO UserDto.
     * @return Сущность UsersEntity.
     */
    @Mapping(target = "id", ignore = true)
    UsersEntity mapToEntityForCreate(UserDto dto);
}
