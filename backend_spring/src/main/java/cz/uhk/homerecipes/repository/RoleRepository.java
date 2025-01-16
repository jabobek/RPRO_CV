package cz.uhk.homerecipes.repository;

import java.util.Optional;

import cz.uhk.homerecipes.models.UserRole;
import cz.uhk.homerecipes.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(UserRole name);
}
