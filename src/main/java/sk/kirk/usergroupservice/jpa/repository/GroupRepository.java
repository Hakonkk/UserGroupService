package sk.kirk.usergroupservice.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.kirk.usergroupservice.jpa.entity.GroupEntity;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {
}
