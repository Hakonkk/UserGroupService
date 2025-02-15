package sk.kirk.usergroupservice.jpa.mapping;

import org.mapstruct.Mapper;
import sk.kirk.usergroupservice.jpa.dto.GroupDto;
import sk.kirk.usergroupservice.jpa.entity.GroupEntity;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupDto mapToDto(GroupEntity entity);

    GroupEntity mapToEntity(GroupDto dto);
}
