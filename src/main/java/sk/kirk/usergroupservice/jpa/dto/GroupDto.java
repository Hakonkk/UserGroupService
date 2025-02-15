package sk.kirk.usergroupservice.jpa.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private Integer id;

    private String name;

    private Set<UserDto> users;
}
