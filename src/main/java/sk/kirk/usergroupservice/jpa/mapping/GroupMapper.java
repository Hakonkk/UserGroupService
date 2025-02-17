package sk.kirk.usergroupservice.jpa.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sk.kirk.usergroupservice.jpa.dto.GroupDto;
import sk.kirk.usergroupservice.jpa.entity.GroupEntity;

/**
 * Mapper для преобразования между сущностью GroupEntity и DTO GroupDto.
 * Использует библиотеку MapStruct для автоматического преобразования полей.
 *
 * @author Kirillov Sergio
 */
@Mapper(componentModel = "spring")
public interface GroupMapper {

    /**
     * Преобразует сущность GroupEntity в DTO GroupDto.
     *
     * @param entity Сущность GroupEntity.
     * @return DTO GroupDto.
     */
    GroupDto mapToDto(GroupEntity entity);

    /**
     * Преобразует DTO GroupDto в сущность GroupEntity.
     * Поле id игнорируется при преобразовании, так как оно генерируется автоматически.
     *
     * @param dto DTO GroupDto.
     * @return Сущность GroupEntity.
     */
    @Mapping(target = "id", ignore = true)
    GroupEntity mapToEntity(GroupDto dto);
}
