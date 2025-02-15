package sk.kirk.usergroupservice.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "groups")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String groupName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groups")
    private Set<UsersEntity> users;
}

