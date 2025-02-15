package sk.kirk.usergroupservice.jpa.mapping;

import org.mapstruct.Mapper;
import sk.kirk.usergroupservice.jpa.dto.UserDto;
import sk.kirk.usergroupservice.jpa.entity.UsersEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapToDto(UsersEntity entity);

    UsersEntity mapToEntity(UserDto dto);
}
