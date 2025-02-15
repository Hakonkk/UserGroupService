package sk.kirk.usergroupservice.jpa.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;

    private String userName;

    private Set<GroupDto> groups;
}
